package com.oxy.uillselect.features.comment

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

interface CommentComponent {
    val value: Value<CommentModel>
    fun onEvent(event: CommentEvent)
}

class DefaultCommentComponent(
    componentContext: ComponentContext,
    onFinished: () -> Unit
) : CommentComponent {
    override val value: Value<CommentModel> = MutableValue(CommentModel())

    override fun onEvent(event: CommentEvent) {

    }
}