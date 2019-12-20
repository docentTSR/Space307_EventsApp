package com.space307.events_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CategoryTypeEventAdapter(private val categoriesType: List<EventCategoryType>)
    : RecyclerView.Adapter<CategoryTypeEventAdapter.ViewHolder>() {

    private var listener: OnClickListener? = null

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_date, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(categoriesType[position], listener)
    }

    override fun getItemCount(): Int = categoriesType.size

    interface OnClickListener {
        fun onClick(eventType: EventCategoryType)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.item_date_date)

        fun bindView(eventType: EventCategoryType, listener: OnClickListener?) {
            name.text = eventType.name
            itemView.setOnClickListener {
                listener?.onClick(eventType)
            }
        }
    }

}