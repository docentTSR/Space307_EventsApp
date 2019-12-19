package com.space307.events_app

interface OnEventItemListener {
    fun onEventSelected(model: EventModel)
    fun onEventRemoved(model: EventModel)
}