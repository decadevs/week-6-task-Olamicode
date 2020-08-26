package com.olamachia.registrationform

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ProfileActivityTest {
    @get: Rule
    val activityRule = ActivityScenarioRule(ProfileActivity::class.java)

    /** Tests to check the visibilities of the views in the ProfileActivity */

    @Test
    fun test_visibility_landingImage() {
        onView(withId(R.id.ivHeaderImage)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_profileName() {
        // onView(withId(R.id.tvProfileName)).check(matches(isDisplayed()))

        onView(withId(R.id.tvProfileName)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_visibility_phoneTextField() {
        onView(withId(R.id.tvPhoneNumber)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_emailField() {
        onView(withId(R.id.tvEmailAddress)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_genderField() {
        onView(withId(R.id.tvGender)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_phoneIcon() {
        onView(withId(R.id.ivPhoneNum)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_emailIcon() {
        onView(withId(R.id.ivEmailAdd)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_genderIcon() {
        onView(withId(R.id.ivGenderTwo)).check(matches(isDisplayed()))
    }
}