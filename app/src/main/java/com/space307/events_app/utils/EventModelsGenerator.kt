package com.space307.events_app.utils

import android.text.format.DateUtils
import com.space307.events_app.EventCategoryType
import com.space307.events_app.EventModel
import kotlin.random.Random

object EventModelsGenerator {

    fun generateEventModel() =
        EventModel(
            name = "title ${Random.nextInt(0, 50)}",
            description = "description",
            logoUrl = "url",
            category = EventCategoryType.values()[Random.nextInt(0, EventCategoryType.values().size - 1)].name,
            startDate = System.currentTimeMillis(),
            endDate = System.currentTimeMillis() + DateUtils.HOUR_IN_MILLIS,
            minPersons = Random.nextInt(1, 5),
            maxPersons = Random.nextInt(6, 12),
            latitude = 0.0,
            longitude = 0.0
        )

    fun generateAntifitnessModel() =
        EventModel(
            name = "Anti fitness",
            description = "Групповые тренировки для сотрудников компании. Проходят на Газовой 10",
            logoUrl = "url",
            category = EventCategoryType.values()[1].name,
            startDate = System.currentTimeMillis(),
            endDate = System.currentTimeMillis() + DateUtils.HOUR_IN_MILLIS,
            minPersons = 1,
            maxPersons = 15,
            latitude = 0.0,
            longitude = 0.0,
            repeatable = true
        )

    fun generatePokerModel() =
        EventModel(
            name = "Poker evening",
            description = "Играем в покер, пьем пивас",
            logoUrl = "",
            category = EventCategoryType.values()[0].name,
            startDate = System.currentTimeMillis(),
            endDate = System.currentTimeMillis() + DateUtils.HOUR_IN_MILLIS,
            minPersons = 5,
            maxPersons = 10,
            latitude = 0.0,
            longitude = 0.0,
            repeatable = false
        )

}