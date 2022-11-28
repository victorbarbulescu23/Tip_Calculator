package com.example.tipcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    //
    lateinit var tipPercent: Spinner
    lateinit var priceInput: EditText
    lateinit var calculateButton: Button

    var price: Double = 0.0
    var tip: Double = 0.0
    var finalPrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tipPercent = findViewById(R.id.tip_percent_spinner)
        priceInput = findViewById(R.id.price_input)
        calculateButton = findViewById(R.id.calculate)

        calculateButton.setOnClickListener {
            if (checkData()){
                tip = if (tipPercent.selectedItem.toString() == "5%"){
                    0.05
                } else if (tipPercent.selectedItem.toString() == "10%"){
                    0.1
                } else if (tipPercent.selectedItem.toString() == "15%"){
                    0.15
                } else if (tipPercent.selectedItem.toString() == "20%"){
                    0.2
                } else {
                    0.25
                }

                price = priceInput.text.toString().toDouble()
                finalPrice = price + (price * tip)

                val currency = DecimalFormat("$##,###.00")
                val finalPriceFormatted = currency.format(finalPrice)

                Intent(this@MainActivity, PriceOutput::class.java).also {
                    it.putExtra("EXTRA_FINAL_PRICE", finalPriceFormatted)
                    it.putExtra("EXTRA_TIP",  tipPercent.selectedItem.toString())

                    startActivity(it)
                }
            }

        }



    }

    //CheckData method validates the inputted data to ensure the app won't crash during unexpected events such as an invalid input
    private fun checkData(): Boolean {
        if (priceInput.text.toString().isEmpty() || priceInput.text.toString().toDouble() <= 0){
            priceInput.error = "Invalid Price Input"
            priceInput.requestFocus()
            return false
        }

        return true
    }




}

