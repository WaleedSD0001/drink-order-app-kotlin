package com.example.drink_order_app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    lateinit var juiceDropdownValue: String
    val juices_list = listOf("Apple", "Strawberry", "Abricot", "Orange")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var juice: AutoCompleteTextView = findViewById(R.id.juice_row)
        var adap = ArrayAdapter(this, R.layout.juice_item, juices_list)


        juice.setAdapter(adap)

        var price: TextView = findViewById(R.id.price)

        juice.setOnItemClickListener { adapterView, view, i, l ->
            juiceDropdownValue = juice.text.toString()
            price.text = when(juiceDropdownValue) {
                "Orange" -> "Price: 15"
                "Strawberry" -> "Price: 20"
                "Apple" -> "Price: 18"
                else -> {
                    "Price: 16"
                }
            }
        }

        val orderButton: Button = findViewById(R.id.order_button)

        orderButton.setOnClickListener {
            var orderIntent = Intent(Intent.ACTION_SENDTO)
            orderIntent.data = Uri.parse("mailto:")
            orderIntent.putExtra(Intent.EXTRA_SUBJECT, "Order ${juice.text}")
            orderIntent.putExtra(Intent.EXTRA_TEXT, "The Order is ready for ${price.text}")
            startActivity(orderIntent)
        }

    }
}