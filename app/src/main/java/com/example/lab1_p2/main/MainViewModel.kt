package com.example.lab1_p2.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab1_p2.R

class MainViewModel: ViewModel() {
    private var _name: String = ""
    private var _email: String = ""
    private var _password: String = ""
    private var _birthday: String = ""

    private val errorMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val errorLiveData: LiveData<Int> = errorMutableLiveData
    private val resultMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val resultLiveData: LiveData<String> = resultMutableLiveData
    private val notEmptyAndRightPasswordMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val notEmptyAndRightPasswordLiveData: LiveData<Boolean> = notEmptyAndRightPasswordMutableLiveData

    fun CheckEmptiness(
        name: String,
        email: String,
        password: String,
        repeatPassword: String,
        birthday: String,
        defaultBirthdayText: String
    ) {
        if (name.isEmpty()) {
            errorMutableLiveData.value = R.string.error_empty_name
        } else if (email.isEmpty()) {
            errorMutableLiveData.value = R.string.error_empty_email
        } else if (password.isEmpty()) {
            errorMutableLiveData.value = R.string.error_empty_password
        } else if (repeatPassword.isEmpty()) {
            errorMutableLiveData.value = R.string.error_empty_Repeated_Password
        } else if (birthday == defaultBirthdayText) {
            errorMutableLiveData.value = R.string.error_empty_birthday
        } else {
            if(password == repeatPassword) {
                _name = name
                _email = email
                _password = password
                _birthday = birthday
                notEmptyAndRightPasswordMutableLiveData.value = true
            } else{
                errorMutableLiveData.value = R.string.error_different_passwords
            }
        }
    }

    fun result(city: String, gender1Text: String, gender1IsChecked: Boolean, gender2Text: String, hobbiesChecks: Array<Boolean>, hobbiesTexts: Array<String>, resultFormat: String) {
        val gender = if (gender1IsChecked) gender1Text else gender2Text
        var hobbies = ""
        if (hobbiesChecks[0])
            hobbies += hobbiesTexts[0]
        if (hobbiesChecks[1])
            hobbies += ", " + hobbiesTexts[1]
        if (hobbiesChecks[2])
            hobbies += ", " + hobbiesTexts[2]
        if (hobbiesChecks[3])
            hobbies += ", " + hobbiesTexts[3]
        if (hobbies.isNotBlank()){
            if(hobbies.substring(0, 2) == ", ")
                hobbies =  hobbies.substring(2, hobbies.lastIndex + 1)
        }
        resultMutableLiveData.value = String.format(
            resultFormat,
            _name, _email,
            _password, gender,
            hobbies, _birthday, city
        )
    }


}