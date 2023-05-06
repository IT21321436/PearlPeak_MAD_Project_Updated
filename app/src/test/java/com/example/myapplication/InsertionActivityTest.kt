//package com.example.myapplication
//
//import android.widget.Button
//import android.widget.EditText
//
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions.*
//import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.matcher.ViewMatchers.*
//import androidx.test.rule.ActivityTestRule
//import com.example.myapplication.R
//import com.example.myapplication.models.EmployeeModel
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.Mockito.*
//import org.mockito.MockitoAnnotations
//class InsertionActivityTest {
//    @get:Rule
//    val activityRule = ActivityTestRule(InsertionActivity::class.java)
//    @Mock
//    lateinit var mockDbRef: DatabaseReference
//    private lateinit var etEmpName: EditText
//    private lateinit var etEmpAge: EditText
//    private lateinit var etEmpSalary: EditText
//    private lateinit var btnSaveData: Button
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        activityRule.activity.dbRef = mockDbRef
//        etEmpName = activityRule.activity.findViewById(R.id.etEmpName)
//        etEmpAge = activityRule.activity.findViewById(R.id.etEmpAge)
//        etEmpSalary = activityRule.activity.findViewById(R.id.etEmpSalary)
//        btnSaveData = activityRule.activity.findViewById(R.id.btnSave)
//    }
//    @Test
//    fun testSaveEmployeeDataSuccess() {
//        val empName = "John"
//        val empAge = "30"
//        val empSalary = "50000"
//        // Mocking the values of the EditTexts
//        etEmpName.setText(empName)
//        etEmpAge.setText(empAge)
//        etEmpSalary.setText(empSalary)
//        // Creating a mock employee object
//        val empId = "123"
//        val employee = EmployeeModel(empId, empName, empAge, empSalary)
//
//        // Mocking the push() method to return the ID of the mock employee object
//        `when`(mockDbRef.push()).thenReturn(Mockito.mock(DatabaseReference::class.java))
//        `when`(mockDbRef.push().key).thenReturn(empId)
//        // Clicking on the Save button
//        onView(withId(R.id.btnSave)).perform(click())
//        // Verifying that the data was inserted successfully
//        verify(mockDbRef).child(empId).setValue(employee)
//        onView(withText("Data inserted successfully")).check(matches(isDisplayed()))
//        // Verifying that the EditTexts are cleared after saving the data
//        assertEquals("", etEmpName.text.toString())
//        assertEquals("", etEmpAge.text.toString())
//        assertEquals("", etEmpSalary.text.toString())
//    }
//
//    @Test
//    fun testSaveEmployeeDataError() {
//        val empName = "John"
//        val empAge = "30"
//        // Mocking the values of the EditTexts
//        etEmpName.setText(empName)
//        etEmpAge.setText(empAge)
//        // Clicking on the Save button
//        onView(withId(R.id.btnSave)).perform(click())
//        // Verifying that an error message is displayed if the salary is empty
//        onView(withText("Please enter salary")).check(matches(isDisplayed()))
//    }
//}