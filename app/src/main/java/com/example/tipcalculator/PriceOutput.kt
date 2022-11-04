package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PriceOutput : AppCompatActivity() {

    lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_output)

        resultText = findViewById(R.id.price_output)

        val totalPrice = intent.getStringExtra("EXTRA_FINAL_PRICE")
        val tip = intent.getStringExtra("EXTRA_TIP")

        val result = "Your new total, including your selected tip of $tip, is $totalPrice"
        resultText.text = result
    }
}