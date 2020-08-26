package com.olamachia.registrationform

import android.util.Patterns
import org.junit.Assert.*
import org.junit.Test

class MainActivityUnitTest {

    val mainActivity = MainActivity()

    /** Tests to check the correctness of the validatePhoneNumber function  */

    @Test
    fun phoneNumber_Validate_StartsWithPlus234_length14_ReturnsTrue() {
        assertEquals(mainActivity.validatePhoneNumber("+2348133400710"), true)
    }

    @Test
    fun phoneNumber_Validate_StartsWith234_length13_ReturnsTrue() {
        assertEquals(mainActivity.validatePhoneNumber("2348133400710"), true)
    }

    @Test
    fun phoneNumber_Validate_StartsWithZero_length11_ReturnsTrue() {
        assertEquals(mainActivity.validatePhoneNumber("08136300710"), true)
    }

    @Test
    fun phoneNumber_Validate_StartsWithPlus234_length_lessThan14_ReturnsFalse() {
        assertEquals(mainActivity.validatePhoneNumber("+234813340071"), false)
    }

    @Test
    fun phoneNumber_Validate_StartsWith234_lessThan13_ReturnsFalse() {
        assertEquals(mainActivity.validatePhoneNumber("234813340071"), false)
    }

    @Test
    fun phoneNumber_Validate_NotStartWith234OrPlus234orZero_ReturnsFalse() {
        assertEquals(mainActivity.validatePhoneNumber("+3348133400711"), false)
    }

    /** Tests to check functions to check empty fields  */

    @Test
    fun empty_phoneNumber_ReturnTrue() {
        assertEquals(mainActivity.checkEmptyPhoneNumber(""), true)
    }

    @Test
    fun nonEmpty_phoneNumber_ReturnFalse() {
        assertEquals(mainActivity.checkEmptyPhoneNumber("081333400711"), false)
    }

    @Test
    fun empty_Email_ReturnTrue() {
        assertEquals(mainActivity.checkEmptyEmail(""), true)
    }

    @Test
    fun nonEmpty_Email_ReturnFalse() {
        assertEquals(mainActivity.checkEmptyEmail("ola@gmail.com"), false)
    }

    @Test
    fun empty_Name_ReturnTrue() {
        assertEquals(mainActivity.checkEmptyName(""), true)
    }

    @Test
    fun nonEmpty_Name_ReturnFalse() {
        assertEquals(mainActivity.checkEmptyName("Ola Machia"), false)
    }

    /** Tests to check function to validate email field */

    @Test
    fun validate_CorrectEmail_ReturnTrue() {
        assertEquals(mainActivity.validateEmail("ola@gmail.com"), true)
    }

    @Test
    fun validate_WrongEmail_WithoutAt_ReturnFalse() {
        assertEquals(mainActivity.validateEmail("olagmail.com"), false)
    }

    @Test
    fun validate_WrongEmail_WithoutDot_ReturnFalse() {
        assertEquals(mainActivity.validateEmail("ola@gmailcom"), false)
    }

}