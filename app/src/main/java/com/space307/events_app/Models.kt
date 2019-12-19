package com.space307.events_app

data class EventModel(
    var id: String = "",

    var name: String = "",
    var description: String = "",

    var logoUrl: String = "",

    var category: String = "",

    var startDate: Long = 0L,
    var endDate: Long = 0L,

    var minPersons: Int = 0,
    var maxPersons: Int = 0,

    var latitude: Double = 0.0,
    var longitude: Double = 0.0,

    var viewed: Boolean = false,

    var repeatable: Boolean = false,

    var repeatableDays: String = ""
)

enum class EventCategoryType {
    GAMES,
    SPORT,
    TRAVEL,
    EXHIBITION,
    FESTIVAL,
    CONCERT,
    ENTERTAINMENT,
    MOVIE,
    BUSINESS,
    DISCUSSIONS,
    FOOD,
    EDUCATION;

    fun getTypeByName(name: String): EventCategoryType = valueOf(name)
}