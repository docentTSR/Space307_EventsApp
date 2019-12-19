package com.space307.events_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.space307.events_app.providers.DatabaseProvider
import com.space307.events_app.providers.OnDatabaseUpdateListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        DatabaseProvider.addEvent(EventModelsGenerator.generateEventModel())
    }

    override fun onStart() {
        super.onStart()

        DatabaseProvider.subscribeEventsChanges(object : OnDatabaseUpdateListener<List<EventModel>> {
            override fun onSuccess(updatedModel: List<EventModel>) = Unit
            override fun onFailure(e: Throwable) = Unit
        })
    }

    override fun onStop() {
        DatabaseProvider.unsubscribeEventsChanges()

        super.onStop()
    }

    // ===========================================================
    // Common
    // ===========================================================


}
