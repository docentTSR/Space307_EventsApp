package com.space307.events_app

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.space307.events_app.providers.DatabaseProvider
import com.space307.events_app.providers.OnDatabaseQueryListener
import com.space307.events_app.providers.OnDatabaseUpdateListener
import com.space307.events_app.utils.EventModelsGenerator
import com.space307.events_app.utils.getStringDate

class MainActivity : AppCompatActivity() {

    lateinit var adapter: EventsAdapter
    lateinit var dateText: TextView
    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//        DatabaseProvider.addEvent(EventModelsGenerator.generateAntifitnessModel())
//        DatabaseProvider.addEvent(EventModelsGenerator.generatePokerModel())

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
        dateText = findViewById(R.id.main_activity_date)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = EventsAdapter()

        recyclerView.adapter = adapter

        adapter.setListener(object : OnEventItemListener {
            override fun onEventSelected(model: EventModel) {
            }

            override fun onEventRemoved(model: EventModel) {
            }
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val event = adapter.getModelByPosition(position)
                dateText.text = getStringDate(event.startDate)
            }
        })

        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddEventActivity::class.java))
        }
    }

    private fun fetchData() {
        DatabaseProvider.getEvents(object : OnDatabaseQueryListener<List<EventModel>> {
            override fun onSuccess(queryModel: List<EventModel>) {
                var sorted = queryModel.sortedBy { it.startDate }
                sorted = sorted.filter { it.startDate < System.currentTimeMillis() }
                adapter.setItems(sorted)
            }

            override fun onFailure(e: Throwable) {
            }
        })
    }

}
