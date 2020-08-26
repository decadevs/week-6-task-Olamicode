package com.olamachia.registrationform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    lateinit var shake: Animation
    lateinit var phoneNumber: String
    lateinit var fullName: String
    lateinit var email: String
    lateinit var gender: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        spGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                /** setting the data from item selected in the spinner dropdown in the gender field */

                gender = adapterView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        /** declaring animations for the views */

        val scaleToBig = AnimationUtils.loadAnimation(this, R.anim.scale_to_big)
        val leftToRight = AnimationUtils.loadAnimation(this, R.anim.left_to_right)
        val leftToRightTwo = AnimationUtils.loadAnimation(this, R.anim.left_to_right_two)
        val leftToRightThree = AnimationUtils.loadAnimation(this, R.anim.left_to_right_three)
        val leftToRightFour = AnimationUtils.loadAnimation(this, R.anim.left_to_right_four)
        val leftToRightFive = AnimationUtils.loadAnimation(this, R.anim.left_to_right_five)
        shake = AnimationUtils.loadAnimation(this, R.anim.shake)


        /** setting animations on the views */

        ivHeaderImage.startAnimation(scaleToBig)
        tvCreateAcct.startAnimation(leftToRight)        // animating "Create Account Text"
        etname.startAnimation(leftToRight)              // animating name input field
        etEmail.startAnimation(leftToRightTwo)          // animating email input field
        etPhone.startAnimation(leftToRightThree)        // animating phone input field
        spGender.startAnimation(leftToRightFour)        // animating gender input field
        ivGender.startAnimation(leftToRightFour)        // animating gender image
        btnCreate.startAnimation(leftToRightFive)       // animating create/submit button

        /** ClickListener to start next activity after validating all inputs */

        btnCreate.setOnClickListener {
            validateInput()
        }

    }


    /** function to check invalidInput and set shake effect on invalid input field */

    private fun invalidInput(view: View) {
        view.startAnimation(shake)
        view.setBackgroundResource(R.drawable.wrong_input_bg)
        view.setOnFocusChangeListener { view, bool ->
            if (bool) view.setBackgroundResource(R.drawable.form_states)
        }
    }


    /** clear focus for each input field onClick of the window */

    fun clearInputFocus(view: View) {
        etname.clearFocus()
        etEmail.clearFocus()
        etPhone.clearFocus()
    }

    /** function that takes validating functions and validates all inputs */

    private fun validateInput() {
        fullName = etname.text.toString()
        phoneNumber = etPhone.text.toString()
        email = etEmail.text.toString()


        var isFieldEmpty = false

        val validEmail = validateEmail(email) // validating email

        val emptyName = checkEmptyName(fullName) // checking empty name field

        val emptyEmail = checkEmptyEmail(email)  // checking empty email field

        val emptyPhone = checkEmptyPhoneNumber(phoneNumber) // checking empty phone number field

        val validNum = validatePhoneNumber(phoneNumber) // validating phone number

        // checking if any field is empty

        if (emptyName || emptyEmail || emptyPhone) {
            isFieldEmpty = true
        }


        if (validNum == true && isFieldEmpty == false && validEmail) {

            // starting the next activity and setting data for the next activity

            Intent(this, ProfileActivity::class.java).also {
                it.putExtra("FULLNAME", fullName)
                it.putExtra("PHONE", phoneNumber)
                it.putExtra("EMAIL", email)
                it.putExtra("GENDER", gender)

                startActivity(it)

                // clearing all fields on exit of the MainActivity

                etname.text.clear()
                etEmail.text.clear()
                etPhone.text.clear()
            }

        } else {
            if (emptyName) invalidInput(etname)
            if (!validEmail || emptyEmail) invalidInput(etEmail)
            if (validNum == false || emptyPhone) invalidInput(etPhone)
        }

    }

    /** function to validate phone number */

    fun validatePhoneNumber(phone: String): Boolean {

        val patternOne = "^0".toRegex()
        val patternTwo = "^234".toRegex()
        val patternThree = "^\\+234".toRegex()

        var result = false

        // checking if number starts with 0 and has a length of 11

        val validNumberOne = patternOne.containsMatchIn(phone) && phone.length == 11

        // checking if number starts with 234 and has a length of 13 or number starts with +234 and has a length of 14

        val validNumberTwo = patternTwo.containsMatchIn(phone) && phone.length == 13

        val validNumberThree = patternThree.containsMatchIn(phone) && phone.length == 14

        if ((validNumberOne || validNumberTwo || validNumberThree)) result = true

        return result
    }

    /** function to check empty name field */

    fun checkEmptyName(name: String): Boolean {
        if (name.isEmpty()) return true
        return false
    }

    /** function to check empty name field */

    fun checkEmptyPhoneNumber(phone: String): Boolean {
        if (phone.isEmpty()) return true
        return false
    }

    /** function to check empty email field */

    fun checkEmptyEmail(email: String): Boolean {
        if (email.isEmpty()) return true
        return false
    }

    /** function to validate email field */

    fun validateEmail(email: String): Boolean {

        val regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$"
        val matchFound = Pattern.compile(regex).matcher(email).matches()

        if (matchFound) return true
        return false
    }

}