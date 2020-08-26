package com.olamachia.registrationform

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import android.widget.Spinner
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    private val name = "Ola Machia"
    private val email = "ola.machia@gmail.com"
    private val phone = "08136400711"
    private val gender = "Male"
    private val PACKAGE_NAME = "com.olamachia.registrationform"

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /** Tests to check the visibilities of the views in the MainActivity */

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_landingImage() {
        onView(withId(R.id.ivHeaderImage)).check(matches(isDisplayed()))
    }


    @Test
    fun test_visibility_nameField() {
        onView(withId(R.id.etname)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_emailField() {
        onView(withId(R.id.etEmail)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_phoneField() {
        onView(withId(R.id.etPhone)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_genderField() {

        onView(withId(R.id.spGender)).check(matches(isDisplayed()))
    }


    @Test
    fun test_visibility_submitButton() {

        onView(withId(R.id.btnCreate)).check(matches(isDisplayed()))
    }

    /** Tests to check the correctness of texts in the MainActivity */


    @Test
    fun test_isTitleTextDisplayed() {
        val onActivityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.tvCreateAcct)).check(matches(withText(R.string.register)))
    }


    @Test
    fun test_buttonText_displayed() {
        val onActivityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnCreate)).check(matches(withText(R.string.submit)))
    }

    /** Tests to check the navigation of the MainActivity to the ProfileActivity*/

    @Test
    fun test_navSecondActivity() {

        onView(withId(R.id.etname)).perform(typeText(name), closeSoftKeyboard())
        onView(withId(R.id.etEmail)).perform(typeText(email), closeSoftKeyboard())
        onView(withId(R.id.etPhone)).perform(typeText(phone), closeSoftKeyboard())

        onView(withId(R.id.spGender)).perform(click())
        onData(anything()).atPosition(0).perform(click())

        onView(withId(R.id.btnCreate)).perform(click())
        onView(withId(R.id.profileActivity)).check(matches(isDisplayed()))


    }


    /** Tests to check hints of views */

    @Test
    fun test_editText_Views() {

        onView(withId(R.id.etname)).check(matches(withHint(R.string.name)))
        onView(withId(R.id.etPhone)).check(matches(withHint(R.string.phone)))
        onView(withId(R.id.etEmail)).check(matches(withHint(R.string.email)))

    }

    @Test
    fun test_spinner_option_correctness() {

        onView(withId(R.id.spGender)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.spGender)).check(matches(withSpinnerText(containsString("Male"))))

        onView(withId(R.id.spGender)).perform(click())
        onData(anything()).atPosition(1).perform(click())
        onView(withId(R.id.spGender)).check(matches(withSpinnerText(containsString("Female"))))

        onView(withId(R.id.spGender)).perform(click())
        onData(anything()).atPosition(2).perform(click())
        onView(withId(R.id.spGender)).check(matches(withSpinnerText(containsString("Others"))))
    }


    @get: Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun test_verify_data_sentTo_nextActivity() {

        onView(withId(R.id.etname)).perform(typeText(name), closeSoftKeyboard())
        onView(withId(R.id.etEmail)).perform(typeText(email), closeSoftKeyboard())
        onView(withId(R.id.etPhone)).perform(typeText(phone), closeSoftKeyboard())

        onView(withId(R.id.spGender)).perform(click())
        onData(anything()).atPosition(0).perform(click())

        onView(withId(R.id.btnCreate)).perform(click())

        intended(
            allOf(
                hasComponent(hasShortClassName(".ProfileActivity")),
                toPackage(PACKAGE_NAME), hasExtra("FULLNAME", name)
            )
        )

        intended(
            allOf(
                hasComponent(hasShortClassName(".ProfileActivity")),
                toPackage(PACKAGE_NAME), hasExtra("PHONE", phone)
            )
        )

        intended(
            allOf(
                hasComponent(hasShortClassName(".ProfileActivity")),
                toPackage(PACKAGE_NAME), hasExtra("EMAIL", email)
            )
        )

        intended(
            allOf(
                hasComponent(hasShortClassName(".ProfileActivity")),
                toPackage(PACKAGE_NAME), hasExtra("GENDER", gender)
            )
        )
    }

}