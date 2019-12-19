package com.space307.events_app.providers

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.space307.events_app.EventModel

object DatabaseProvider {

    private const val EVENTS_COLLECTION = "events"

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var subscribeChangesListener: ListenerRegistration? = null

    fun addEvent(event: EventModel, listener: OnDatabaseInsertListener<EventModel>? = null) {
        db.collection(EVENTS_COLLECTION)
            .add(event)
            .addOnSuccessListener { ref ->

                getEvent(ref.id, object : OnDatabaseQueryListener<EventModel> {
                    override fun onSuccess(queryModel: EventModel) {
                        listener?.onSuccess(queryModel)
                    }

                    override fun onFailure(e: Throwable) {
                        listener?.onFailure(e)
                    }
                })

            }
            .addOnFailureListener { e ->
                listener?.onFailure(e)
            }
    }

    fun getEvent(eventId: String, listener: OnDatabaseQueryListener<EventModel>) {
        db.collection(EVENTS_COLLECTION)
            .document(eventId)
            .get()
            .addOnSuccessListener { result ->
                listener.onSuccess(
                    result.toObject(EventModel::class.java)!!.apply {
                        id = eventId
                    }
                )
            }
            .addOnFailureListener { e ->
                listener.onFailure(e)
            }
    }

    fun getEvents(listener: OnDatabaseQueryListener<List<EventModel>>) {
        db.collection(EVENTS_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<EventModel>()

                result.documents.forEach { document ->
                    list.add(
                        document.toObject(EventModel::class.java)!!.apply {
                            id = document.id
                        }
                    )
                }

                listener.onSuccess(list)
            }
            .addOnFailureListener { e ->
                listener.onFailure(e)
            }
    }

    fun removeEvent(eventId: String, listener: OnDatabaseRemoveListener) {
        db.collection(EVENTS_COLLECTION)
            .document(eventId)
            .delete()
            .addOnSuccessListener { listener.onSuccess() }
            .addOnFailureListener { listener.onFailure(it) }
    }

    fun setViewedEvent(event: EventModel, listener: OnDatabaseUpdateListener<EventModel>) {
        require(event.id.isNotEmpty())

        db.collection(EVENTS_COLLECTION)
            .document(event.id)
            .update("viewed", true)
            .addOnSuccessListener {

                getEvent(event.id, object : OnDatabaseQueryListener<EventModel> {
                    override fun onSuccess(queryModel: EventModel) {
                        listener.onSuccess(queryModel)
                    }

                    override fun onFailure(e: Throwable) {
                        listener.onFailure(e)
                    }
                })

            }
            .addOnFailureListener { e ->
                listener.onFailure(e)
            }
    }

    // ===========================================================
    // Subscriptions
    // ===========================================================

    fun subscribeEventsChanges(listener: OnDatabaseUpdateListener<List<EventModel>>) {
        subscribeChangesListener?.remove()

        subscribeChangesListener = db.collection(EVENTS_COLLECTION)
            .addSnapshotListener { querySnapshot, e ->
                if (e != null) {
                    listener.onFailure(e)

                    return@addSnapshotListener
                }

                val list = mutableListOf<EventModel>()

                if (list.isNotEmpty()) {
                    querySnapshot?.documents?.forEach { document ->
                        list.add(
                            document.toObject(EventModel::class.java)!!.apply {
                                id = document.id
                            }
                        )
                    }

                    listener.onSuccess(list)
                }
            }
    }

    fun unsubscribeEventsChanges() = subscribeChangesListener?.remove()

}