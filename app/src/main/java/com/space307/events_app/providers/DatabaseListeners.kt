package com.space307.events_app.providers

interface OnDatabaseQueryListener<T> {
    fun onSuccess(queryModel: T)
    fun onFailure(e: Throwable)
}

interface OnDatabaseInsertListener<T> {
    fun onSuccess(model: T)
    fun onFailure(e: Throwable)
}

interface OnDatabaseUpdateListener<T> {
    fun onSuccess(updatedModel: T)
    fun onFailure(e: Throwable)
}

interface OnDatabaseRemoveListener {
    fun onSuccess()
    fun onFailure(e: Throwable)
}
