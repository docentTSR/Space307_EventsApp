package com.space307.events_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space307.events_app.providers.DatabaseProvider
import com.space307.events_app.providers.OnDatabaseQueryListener
import com.space307.events_app.providers.OnDatabaseUpdateListener

class MainActivity : AppCompatActivity() {

    lateinit var adapter: EventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//        DatabaseProvider.addEvent(EventModelsGenerator.generateEventModel())

        initViews()
        fetchData()
    }

    override fun onStart() {
        super.onStart()

        DatabaseProvider.subscribeEventsChanges(object :
            OnDatabaseUpdateListener<List<EventModel>> {
            override fun onSuccess(updatedModel: List<EventModel>) = Unit
            override fun onFailure(e: Throwable) = Unit
        })
    }

    override fun onStop() {
        DatabaseProvider.unsubscribeEventsChanges()

        super.onStop()
    }

    private fun initViews() {
        val recyclerView = findViewById<RecyclerView>(R.id.events_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = EventsAdapter()

        recyclerView.adapter = adapter

        adapter.setListener(object : OnEventItemListener {
            override fun onEventSelected(model: EventModel) {
            }

            override fun onEventRemoved(model: EventModel) {
            }
        })
    }

    private fun fetchData() {
        DatabaseProvider.getEvents(object : OnDatabaseQueryListener<List<EventModel>> {
            override fun onSuccess(queryModel: List<EventModel>) {
                adapter.setItems(queryModel)
            }

            override fun onFailure(e: Throwable) {
            }
        })
    }

}
