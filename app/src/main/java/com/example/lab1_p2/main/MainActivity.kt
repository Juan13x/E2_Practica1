package com.example.lab1_p2.main

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.lab1_p2.R
import com.example.lab1_p2.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        model = ViewModelProvider(this)[MainViewModel::class.java]

        val calendar = Calendar.getInstance()

        val setDate = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)

            val format = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(format)
            val birthdate = sdf.format(calendar.time).toString()
            binding.birthdayButton.text = birthdate
        }

        with(binding){
            genderRadioGroup.check(genderOption1RadioButton.id)

            birthdayButton.setOnClickListener{
                DatePickerDialog(
                    this@MainActivity,
                    setDate,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                ).show()
            }

            saveButton.setOnClickListener{
                infoResultTextView.text = ""
                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordTextInputEditText.text.toString()
                val repeatPassword = repeatPasswordTextInputEditText.text.toString()
                val birthday = birthdayButton.text.toString()
                val defaultBirthdayText = getString(R.string.button_birthday)

                model.CheckEmptiness(name, email, password, repeatPassword, birthday, defaultBirthdayText)
            }

            model.errorLiveData.observe(this@MainActivity){
                error ->
                Toast.makeText(this@MainActivity,getString(error),Toast.LENGTH_SHORT).show()
            }

            model.notEmptyAndRightPasswordLiveData.observe(this@MainActivity){
                val city = birthPlaceSpinner.selectedItem.toString()
                val gender1_text = genderOption1RadioButton.text.toString()
                val gender1_isChecked = genderOption1RadioButton.isChecked
                val gender2_text = genderOption2RadioButton.text.toString()
                val hobbiesChecks = arrayOf<Boolean>(
                    hobbie1Chip.isChecked,
                    hobbie2Chip.isChecked,
                    hobbie3Chip.isChecked,
                    hobbie4Chip.isChecked)
                val hobbiesTexts = arrayOf<String>(
                    hobbie1Chip.text.toString(),
                    hobbie2Chip.text.toString(),
                    hobbie3Chip.text.toString(),
                    hobbie4Chip.text.toString()
                )
                val resultFormat = getString(R.string.result)
                model.result(
                    city,
                    gender1_text,
                    gender1_isChecked,
                    gender2_text,
                    hobbiesChecks,
                    hobbiesTexts,
                    resultFormat
                )
            }

            model.resultLiveData.observe(this@MainActivity){
                result ->
                infoResultTextView.text = result
            }
        }
    }
}