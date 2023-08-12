package com.oxy.uillselect.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.oxy.uillselect.core.wrapper.Response.Companion.produce
import com.oxy.uillselect.core.wrapper.exception
import com.oxy.uillselect.core.wrapper.isSuccess
import com.oxy.uillselect.core.wrapper.success
import com.oxy.uillselect.data.api.LoginDTO
import com.oxy.uillselect.data.api.SignupDTO
import com.oxy.uillselect.data.api.UserApi
import com.oxy.uillselect.data.scheme.Users
import com.oxy.uillselect.sqldelight.data.User
import com.oxy.uillselect.sqldelight.data.UserQueries
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class UserRepository(
    private val queries: UserQueries,
    private val api: UserApi,
    private val settings: Settings,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    private val currentUserId: MutableStateFlow<Long> = MutableStateFlow(DEFAULT_USER_ID)
    private val currentToken: MutableStateFlow<String?> = MutableStateFlow(DEFAULT_TOKEN)

    init {
        // restore userId and token from settings
        currentUserId.value = settings[SETTINGS_USER_ID] ?: DEFAULT_USER_ID
        currentToken.value = settings[SETTINGS_TOKEN]
    }

    val currentUser: Flow<User?> = queries.getAllByRole(Users.Role.ADMIN)
        .asFlow()
        .mapToList(dispatcher)
        .combine(currentUserId) { users, userId ->
            users.find { it.id == userId }
        }
        .flowOn(dispatcher)

    suspend fun login(account: String, password: String): Result<Unit> = withContext(dispatcher) {
        val response = produce { api.login(account, password) }
        if (response.isSuccess) {
            val login: LoginDTO = response.success()
            val user = login.user.toUser(Users.Role.ADMIN)
            val token = login.token
            saveAccount(user, token)
            Result.success(Unit)
        } else Result.failure(response.exception())
    }

    suspend fun signup(account: String, password: String): Result<Unit> = withContext(dispatcher) {
        val response = produce { api.signup(account, password) }
        if (response.isSuccess) {
            val login: SignupDTO = response.success()
            val user = login.user.toUser(Users.Role.ADMIN)
            val token = login.token
            saveAccount(user, token)
            Result.success(Unit)
        } else Result.failure(response.exception())
    }

    suspend fun fetchUser(userId: Long, role: Long): Result<Unit> = withContext(dispatcher) {
        val response = produce { api.getById(userId) }
        if (response.isSuccess) {
            val user = response.success().toUser(role)
            // update db
            queries.insert(user)

            Result.success(Unit)
        } else Result.failure(response.exception())
    }

    private fun saveAccount(user: User, token: String) {
        val userId = user.id
        if (queries.getById(userId).executeAsOneOrNull() != null) {
            queries.deleteById(userId)
        }
        queries.insert(user)

        settings[SETTINGS_USER_ID] = userId
        settings[SETTINGS_TOKEN] = token

        currentUserId.value = userId
        currentToken.value = token
    }

    companion object {
        private const val SETTINGS_TOKEN = "token"
        private const val SETTINGS_USER_ID = "userId"

        private const val DEFAULT_USER_ID = -1L
        private val DEFAULT_TOKEN: String? = null
    }
}