package com.example.assignment.Utility

import android.util.Log



object UserEventLogger {

    fun log(event: UserInteractionEvent) {
        val logMessage = when (event) {
            is UserInteractionEvent.ScreenViewed -> "Screen Viewed: ${event.screenName}"
            is UserInteractionEvent.ButtonClicked -> "Button Clicked: id=${event.buttonId}, label=${event.label}"
            is UserInteractionEvent.ItemSelected -> "Item Selected: id=${event.itemId}, type=${event.itemType}"
            is UserInteractionEvent.FormSubmitted -> "Form Submitted: ${event.formName}"
            is UserInteractionEvent.SearchPerformed -> "Search Query: ${event.query}"
        }

        Log.d("UserEventLogger", logMessage)

        // Optional: Forward to analytics platform (e.g., Firebase)
        // FirebaseAnalytics.getInstance(context).logEvent(...)
    }
}
