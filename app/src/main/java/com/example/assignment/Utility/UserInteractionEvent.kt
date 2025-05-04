package com.example.assignment.Utility



sealed class UserInteractionEvent {
    data class ScreenViewed(val screenName: String) : UserInteractionEvent()
    data class ButtonClicked(val buttonId: String, val label: String) : UserInteractionEvent()
    data class ItemSelected(val itemId: String, val itemType: String) : UserInteractionEvent()
    data class FormSubmitted(val formName: String) : UserInteractionEvent()
    data class SearchPerformed(val query: String) : UserInteractionEvent()
}
