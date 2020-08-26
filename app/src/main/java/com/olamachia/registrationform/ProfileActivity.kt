package com.olamachia.registrationform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        val fullName = intent.getStringExtra("FULLNAME")
        val phoneNumber = intent.getStringExtra("PHONE")
        val email = intent.getStringExtra("EMAIL")
        val gender = intent.getStringExtra("GENDER")


        tvProfileName.text = fullName
        tvPhoneNumber.text = phoneNumber
        tvEmailAddress.text = email
        tvGender.text = gender

    }
}