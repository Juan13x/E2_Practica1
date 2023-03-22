package com.example.lab1_p2.main

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lab1_p2.R
import com.example.lab1_p2.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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

                if (name.isEmpty()){
                    Toast.makeText(this@MainActivity, getString(R.string.error_empty_name), Toast.LENGTH_SHORT).show()
                }
                else if (email.isEmpty()){
                    Toast.makeText(this@MainActivity, getString(R.string.error_empty_email), Toast.LENGTH_SHORT).show()
                }
                else if (password.isEmpty()){
                    Toast.makeText(this@MainActivity, getString(R.string.error_empty_password), Toast.LENGTH_SHORT).show()
                }
                else if (repeatPassword.isEmpty()){
                    Toast.makeText(this@MainActivity, getString(R.string.error_empty_Repeated_Password), Toast.LENGTH_SHORT).show()
                }
                else if (birthday == getString(R.string.button_birthday)){
                    Toast.makeText(this@MainActivity, getString(R.string.error_empty_birthday), Toast.LENGTH_SHORT).show()
                }
                else{
                    if(password == repeatPassword){
                        val city = birthPlaceSpinner.selectedItem.toString()
                        val gender =
                            if (genderOption1RadioButton.isChecked)
                                genderOption1RadioButton.text
                            else
                                genderOption2RadioButton.text
                        var hobbies = ""
                        if (hobbie1Chip.isChecked)
                            hobbies += hobbie1Chip.text
                        if (hobbie1Chip.isChecked)
                            hobbies += ", " + hobbie1Chip.text
                        if (hobbie1Chip.isChecked)
                            hobbies += ", " + hobbie1Chip.text
                        if (hobbie1Chip.isChecked)
                            hobbies += ", " + hobbie1Chip.text
                        if (hobbies.isNotBlank()){
                            if(hobbies.substring(0, 2) == ", ")
                                hobbies =  hobbies.substring(2, hobbies.lastIndex + 1)
                        }
                        infoResultTextView.text =
                            getString(
                                R.string.result,
                                name,
                                email,
                                password,
                                gender,
                                hobbies,
                                birthday,
                                city)
                    }
                    else{
                        Toast.makeText(this@MainActivity, getString(R.string.error_different_passwords), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}