package com.space307.events_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space307.events_app.utils.getCategoryImage

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

    fun getModelByPosition(position : Int) : EventModel = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_event, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val eventModel = items[position]

        holder.bindView(eventModel, listener)
    }
}

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.item_event_title)
    private val description: TextView = itemView.findViewById(R.id.item_event_subtitle)
    private val categoryIcon: ImageView = itemView.findViewById(R.id.item_event_category_icon)
    private val logo: ImageView = itemView.findViewById(R.id.item_event_image)

    fun bindView(model: EventModel, listener : OnEventItemListener?) {
        title.text = model.name
        description.text = model.description
        if (model.logoUrl.isNotEmpty()) {

        } else {
            logo.setImageResource(getCategoryImage(EventCategoryType.valueOf(model.category)))
        }

        categoryIcon.setImageResource(R.drawable.ic_replay_black_24dp)
        if (model.repeatable) {
            categoryIcon.visibility = View.VISIBLE
            categoryIcon.visibility = View.GONE
        } else {
            categoryIcon.visibility = View.GONE
        }

        itemView.setOnClickListener {
            listener?.onEventSelected(model)
        }
    }

}