package com.space307.events_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventsAdapter : RecyclerView.Adapter<EventViewHolder>() {

    private var items = mutableListOf<EventModel>()
    private var listener: OnEventItemListener? = null

    fun setItems(items: List<EventModel>) {
        this.items.clear()
        this.items.addAll(items)

        notifyDataSetChanged()
    }

    fun setListener(listener: OnEventItemListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.event_item, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val eventModel = items[position]

        holder.bindView(eventModel)
    }
}

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.event_title)

    fun bindView(model: EventModel) {
        title.text = model.name
    }
}