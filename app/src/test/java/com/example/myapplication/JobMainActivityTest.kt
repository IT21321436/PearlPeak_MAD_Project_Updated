//package com.example.myapplication
//package com.example.myapplication
//
//import android.content.Intent
//import android.widget.Button
//import androidx.test.core.app.ActivityScenario
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions.click
//import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.matcher.ViewMatchers.*
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.myapplication.R
//import com.example.myapplication.adapters.JobMainActivity
//import org.hamcrest.CoreMatchers.not
//import org.junit.Assert.assertEquals
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class JobMainActivityTest {
//
//    @Test
//    fun testJobInsertDataButton() {
//        // Launch the activity under test
//        val intent =
//            Intent(ApplicationProvider.getApplicationContext(), JobMainActivity::class.java)
//        val scenario = ActivityScenario.launch<JobMainActivity>(intent)
//
//        // Click on the Insert Data button
//        onView(withId(R.id.btnJobInsertData)).perform(click())
//
//        // Verify that the correct activity is launched
//        onView(withId(R.id.jobInsertionLayout)).check(matches(isDisplayed()))
//
//        // Close the activity
//        scenario.close()
//    }
//
//    @Test
//    fun testJobFetchDataButton() {
//        // Launch the activity under test
//        val intent =
//            Intent(ApplicationProvider.getApplicationContext(), JobMainActivity::class.java)
//        val scenario = ActivityScenario.launch<JobMainActivity>(intent)
//
//        // Click on the Fetch Data button
//        onView(withId(R.id.btnJobFetchData)).perform(click())
//
//        // Verify that the correct activity is launched
//        onView(withId(R.id.jobFetchingLayout)).check(matches(isDisplayed()))
//
//        // Close the activity
//        scenario.close()
//    }
//
//    @Test
//    fun testJobInsertDataButtonVisibility() {
//        // Launch the activity under test
//        val intent =
//            Intent(ApplicationProvider.getApplicationContext(), JobMainActivity::class.java)
//        val scenario = ActivityScenario.launch<JobMainActivity>(intent)
//
//        // Check that the Insert Data button is visible
//        onView(withId(R.id.btnJobInsertData)).check(matches(isDisplayed()))
//
//        // Close the activity
//        scenario.close()
//    }
//
//    @Test
//    fun testJobFetchDataButtonVisibility() {
//        // Launch the activity under test
//        val intent =
//            Intent(ApplicationProvider.getApplicationContext(), JobMainActivity::class.java)
//        val scenario = ActivityScenario.launch<JobMainActivity>(intent)
//
//        // Check that the Fetch Data button is visible
//        onView(withId(R.id.btnJobFetchData)).check(matches(isDisplayed()))
//
//        // Close the activity
//        scenario.close()
//    }
//
//    @Test
//    fun testJobInsertDataButtonNotClickableWhenFetchingData() {
//        // Launch the activity under test
//        val intent =
//            Intent(ApplicationProvider.getApplicationContext(), JobMainActivity::class.java)
//        val scenario = ActivityScenario.launch<JobMainActivity>(intent)
//
//        // Click on the Fetch Data button
//        onView(withId(R.id.btnJobFetchData)).perform(click())
//
//        // Check that the Insert Data button is not clickable
//        onView(withId(R.id.btnJobInsertData)).check(matches(not(isClickable())))
//
//        // Close the activity
//        scenario.close()
//    }
//
////    @Test
////    fun testJobFetchDataButtonNotClickableWhenInsertingData() {
////        // Launch the activity under test
////        val intent = Intent(ApplicationProvider.getApplicationContext(), JobMainActivity::class.java)
////        val scenario = ActivityScenario.launch<JobMainActivity>(intent)
////
////        // Click on the Insert Data button
////        onView(withId(R.id.btnJobInsertData)).perform(click())
////
////        // Check that the Fetch Data button is not clickable
////        onView(withId(R.id.btnJobFetchData)).check(matches(not(isClickable()))
//}