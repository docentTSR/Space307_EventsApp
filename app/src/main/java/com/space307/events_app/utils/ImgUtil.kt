package com.space307.events_app.utils

import com.space307.events_app.EventCategoryType
import com.space307.events_app.R

fun getCategoryImage(eventType : EventCategoryType): Int = when(eventType) {
    EventCategoryType.SPORT -> R.drawable.ic_fitness
    EventCategoryType.TRAVEL -> R.drawable.ic_travel
    EventCategoryType.GAMES -> R.drawable.ic_games
    EventCategoryType.EDUCATION -> R.drawable.ic_education
    EventCategoryType.ENTERTAINMENT -> R.drawable.ic_entertainment
    EventCategoryType.MOVIE -> R.drawable.ic_movie
    EventCategoryType.DISCUSSIONS -> R.drawable.ic_discussion
    EventCategoryType.BUSINESS -> R.drawable.ic_business
    EventCategoryType.FOOD -> R.drawable.ic_food

    else -> R.drawable.ic_travel
}