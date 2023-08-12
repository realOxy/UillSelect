package com.oxy.uillselect.data.api

import com.oxy.uillselect.sqldelight.data.User
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import kotlinx.serialization.Serializable

interface UserApi {
    @GET("users/{id}/")
    suspend fun getById(@Path("id") id: Long): UserDTO

    @POST("users/login/")
    suspend fun login(@Query("account") account: String, @Query("password") password: String): LoginDTO

    @POST("users/signup/")
    suspend fun signup(@Query("account") account: String, @Query("password") password: String): SignupDTO
}

@Serializable
data class LoginDTO(
    val user: UserDTO,
    val token: String
)

@Serializable
data class SignupDTO(
    val user: UserDTO,
    val token: String
)

@Serializable
data class UserDTO(
    val id: Long,
    val name: String
) {
    fun toUser(role: Long): User = User(
        id = id,
        name = name,
        role = role
    )
}