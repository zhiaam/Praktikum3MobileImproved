package com.example.konversimu1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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

        binding.nilaiMatauang.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
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

        val indonesianLocale = Locale("in", "ID")
        val formattedResult = NumberFormat.getCurrencyInstance(indonesianLocale).format(cash)
        binding.kursResult.text = getString(R.string.cashAmount, formattedResult)
    }

    private fun handleKeyEvent(view: View, keyCode:Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER){
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}