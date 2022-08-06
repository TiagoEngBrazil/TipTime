package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {

        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        val selctedId = binding.tipOptions.checkedRadioButtonId
        if (cost == null) {
            binding.textTipAmount.text = ""
            Toast.makeText(this, R.string.validation_fill_cost_of_service, Toast.LENGTH_SHORT).show()
            return
        }
        val tipPercentage = when (selctedId) {
            R.id.button_radio_1 -> 0.20
            R.id.button_radio_2 -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage

        val roundUp = binding.BottomSwitch.isChecked
        if (roundUp) tip = ceil(tip)
        val formatTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.textTipAmount.text = getString(R.string.tip_amount, formatTip)
    }
}