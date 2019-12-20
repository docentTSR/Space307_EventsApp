package com.space307.events_app

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.PopupWindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space307.events_app.providers.DatabaseProvider
import com.space307.events_app.utils.EventModelsGenerator
import com.space307.events_app.utils.getStringDate
import java.util.*


class AddEventActivity : AppCompatActivity() {

    private lateinit var nameOfEventText : EditText
    private lateinit var description: EditText

    private lateinit var dateClicker: FrameLayout
    private lateinit var dateInput: EditText

    private lateinit var categoryClicker: FrameLayout
    private lateinit var categoryInput: EditText

    private lateinit var btnCreate: Button;

    private val pickerCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_event)

        initViews()
        initDateTime()
    }

    private fun initViews() {
        nameOfEventText = findViewById(R.id.activity_add_event_title_text)
        description = findViewById(R.id.activity_add_event_description_text)

        dateClicker = findViewById(R.id.activity_add_event_date_clicker)
        dateInput = findViewById(R.id.activity_add_event_date_text)
        dateClicker.setOnClickListener {

        }

        categoryClicker = findViewById(R.id.activity_add_event_category_clicker)
        categoryInput = findViewById(R.id.activity_add_event_category_text)
        categoryClicker.setOnClickListener {
            showCategoriesList(EventCategoryType.values().toList())
        }

        btnCreate = findViewById(R.id.activity_add_event_btn_create)
        btnCreate.setOnClickListener {
            val event = EventModel(
                name = nameOfEventText.text.toString(),
                description = description.text.toString(),
                logoUrl = "",
                category = categoryInput.text.toString(),
                startDate = System.currentTimeMillis(),
                endDate = System.currentTimeMillis() + DateUtils.HOUR_IN_MILLIS,
                minPersons = 5,
                maxPersons = 10,
                latitude = 0.0,
                longitude = 0.0,
                repeatable = false
            )
            DatabaseProvider.addEvent(event)
            finish()
        }
    }

    private fun showCategoriesList(eventsType: List<EventCategoryType>) {
        val v = LayoutInflater.from(this).inflate(R.layout.pop_up_categories, null)
        val recyclerView = v.findViewById<RecyclerView>(R.id.rv_symbols)

        val pw = PopupWindow(v,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        val adapter = CategoryTypeEventAdapter(eventsType)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.overScrollMode = View.OVER_SCROLL_NEVER
        adapter.setOnClickListener(object : CategoryTypeEventAdapter.OnClickListener {
            override fun onClick(eventType: EventCategoryType) {
                categoryInput.setText(eventType.name)
                pw.dismiss()
            }
        })

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        pw.isOutsideTouchable = true
        PopupWindowCompat.setOverlapAnchor(pw, true)
        PopupWindowCompat.showAsDropDown(pw, nameOfEventText, 0, 0, Gravity.NO_GRAVITY)
    }

    private fun initDateTime() {
        pickerCalendar.timeInMillis = System.currentTimeMillis()

        dateClicker.setOnClickListener {
            val datePicker = DatePickerDialog(this, R.style.DialogTheme, DatePickerDialog.OnDateSetListener
            { _, year, month, dayOfMonth ->
                pickerCalendar.set(Calendar.YEAR, year)
                pickerCalendar.set(Calendar.MONTH, month)
                pickerCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                showTimePicker()
            }, pickerCalendar.get(Calendar.YEAR), pickerCalendar.get(Calendar.MONTH), pickerCalendar.get(
                Calendar.DAY_OF_MONTH))
            datePicker.datePicker.minDate = System.currentTimeMillis()

            datePicker.show()
            datePicker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.shadow_black))
            datePicker.getButton(DatePickerDialog.BUTTON_NEUTRAL).setTextColor(ContextCompat.getColor(this, R.color.shadow_black))
            datePicker.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.shadow_black))
        }
    }

    private fun showTimePicker() {
        val timePicker = TimePickerDialog(this, R.style.DialogTheme, TimePickerDialog.OnTimeSetListener
        { _, hourOfDay, minute ->
            pickerCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            pickerCalendar.set(Calendar.MINUTE, minute)
            if (pickerCalendar.timeInMillis >= Calendar.getInstance().timeInMillis) {
                dateInput.setText(getStringDate(pickerCalendar.timeInMillis))
            }

        }, pickerCalendar.get(Calendar.HOUR_OF_DAY), pickerCalendar.get(Calendar.MINUTE), true)

        timePicker.show()
        timePicker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.shadow_black))
        timePicker.getButton(DatePickerDialog.BUTTON_NEUTRAL).setTextColor(ContextCompat.getColor(this, R.color.shadow_black))
        timePicker.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.shadow_black))
    }
}