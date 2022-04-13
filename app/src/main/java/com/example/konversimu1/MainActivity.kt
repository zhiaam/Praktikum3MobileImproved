package com.example.konversimu1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.konversimu1.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateKurs() }
    }

    private fun calculateKurs() {
        val stringInTextField = binding.nilaiMatauang.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.kursResult.text = ""
            return
        }

        //kurs menggunakan data pada tanggal 11/04/2022
        val kursOptions = when (binding.kursOptions.checkedRadioButtonId) {
            R.id.option_euro -> 15622.55
            R.id.option_dollar -> 14363.25
            R.id.option_yen -> 114.43
            else -> 3829.94
        }

        var cash = kursOptions * cost
//        if (binding.roundUpSwitch.isChecked) {
//            tip = kotlin.math.ceil(tip)
//        }
//      Hope we don't need em

        val indonesianLocale = Locale("in", "ID")
        val formattedResult = NumberFormat.getCurrencyInstance(indonesianLocale).format(cash)
        binding.kursResult.text = getString(R.string.cashAmount, formattedResult)
    }

}