package com.space307.events_app

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.space307.events_app.utils.getCategoryImage
import com.space307.events_app.utils.getStringDate


class EventCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.event_card)

        val model = intent.extras?.getSerializable("selectedEvent") as EventModel

        model.viewed = true

        val image: ImageView = findViewById(R.id.EventImage)

        if (model.logoUrl.isNotEmpty()) {
            //TODO load img from url
        } else {
            image.setImageResource(getCategoryImage(EventCategoryType.valueOf(model.category)))
        }

        val name: TextView = findViewById(R.id.EventName)
        val category: TextView = findViewById(R.id.EventCategory)
        val descr: TextView = findViewById(R.id.EventDescr)
        val time: TextView = findViewById(R.id.EventTime)

        name.text = model.name
        category.text = model.category
        descr.text = model.description

        var timeText = getStringDate(model.startDate)
        if(model.endDate != 0L) {
            timeText = timeText + " - " + getStringDate(model.endDate)
        }

        time.text = timeText

        val accept: Button = findViewById(R.id.AcceptButton)
        accept.setOnClickListener{
            //TODO take invitation to event

            finish()
        }

        val decline: Button = findViewById(R.id.DeclineButton)
        decline.setOnClickListener{
            finish()
        }
    }

}