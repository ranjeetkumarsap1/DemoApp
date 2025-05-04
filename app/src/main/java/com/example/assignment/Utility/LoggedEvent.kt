package com.example.assignment.Utility

data class LoggedEvent(
    val event: UserInteractionEvent,
    val timestamp: Long = System.currentTimeMillis(),
    val userId: String? = null
)

