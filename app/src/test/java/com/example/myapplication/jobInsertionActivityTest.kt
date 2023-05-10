package com.example.myapplication


import android.widget.*

import com.example.myapplication.activities.jobInsertionActivity
import com.example.myapplication.models.jobModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class jobInsertionActivityTest{


    private lateinit var activity: jobInsertionActivity

    @Mock
    private lateinit var companyNameEditText: EditText

    @Mock
    private lateinit var companyEmailEditText: EditText

    @Mock
    private lateinit var companyPhoneEditText: EditText

    @Mock
    private lateinit var companyCategorySpinner: Spinner

    @Mock
    private lateinit var companyDescriptionEditText: EditText

    @Mock
    private lateinit var jobCheckBox: CheckBox

    @Mock
    private lateinit var btnSaveData: Button


    @Mock
    private lateinit var dbRef: DatabaseReference

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        activity = Mockito.spy(jobInsertionActivity())
        Mockito.`when`(activity.findViewById<EditText>(R.id.editCompanyName))
            .thenReturn(companyNameEditText)
        Mockito.`when`(activity.findViewById<EditText>(R.id.editCompanyEmail))
            .thenReturn(companyEmailEditText)
        Mockito.`when`(activity.findViewById<EditText>(R.id.editPhone))
            .thenReturn(companyPhoneEditText)
        Mockito.`when`(activity.findViewById<Spinner>(R.id.editCategory))
            .thenReturn(companyCategorySpinner)
        Mockito.`when`(activity.findViewById<EditText>(R.id.editCompanyDiscription))
            .thenReturn(companyDescriptionEditText)
        Mockito.`when`(activity.findViewById<CheckBox>(R.id.companyCheckBox))
            .thenReturn(jobCheckBox)
        Mockito.`when`(activity.findViewById<Button>(R.id.btnSubmit)).thenReturn(btnSaveData)
        Mockito.`when`(FirebaseDatabase.getInstance().getReference("JobPortal")).thenReturn(dbRef)
    }

    @Test
    fun `saveCompanyData with empty companyName should display error`() {
        // Given
        Mockito.`when`(companyNameEditText.text.toString()).thenReturn("")
        Mockito.`when`(companyEmailEditText.text.toString()).thenReturn("test@test.com")
        Mockito.`when`(companyPhoneEditText.text.toString()).thenReturn("1234567890")
        Mockito.`when`(companyCategorySpinner.selectedItem.toString()).thenReturn("Test Category")
        Mockito.`when`(companyDescriptionEditText.text.toString()).thenReturn("Test Description")
        Mockito.`when`(jobCheckBox.isChecked).thenReturn(true)

        // When
        activity.saveCompanyData()

        // Then
        Mockito.verify(companyNameEditText).error = "Please Enter User Name"
        Mockito.verify(dbRef, Mockito.never()).child(Mockito.anyString())
        Mockito.verify(dbRef, Mockito.never()).setValue(Mockito.any(jobModel::class.java))
        Mockito.verify(btnSaveData, Mockito.never()).setOnClickListener(Mockito.any())
        Mockito.verify(btnSaveData, Mockito.never()).performClick()
        Mockito.verify(activity, Mockito.never()).setContentView(Mockito.anyInt())

    }

    @Test
    fun `saveCompanyData with empty companyEmail should display error`() {
        // Given
        Mockito.`when`(companyNameEditText.text.toString()).thenReturn("Test Company")
        Mockito.`when`(companyEmailEditText.text.toString()).thenReturn("")
        Mockito.`when`(companyPhoneEditText.text.toString()).thenReturn("1234567890")
        Mockito.`when`(companyCategorySpinner.selectedItem.toString()).thenReturn("Test Category")
        Mockito.`when`(companyDescriptionEditText.text.toString()).thenReturn("Test Description")
        Mockito.`when`(jobCheckBox.isChecked).thenReturn(true)

        // When
        activity.saveCompanyData()

        // Then
        Mockito.verify(companyEmailEditText).error = "Please Enter Email Address"
        Mockito.verify(dbRef, Mockito.never()).child(Mockito.anyString())
        Mockito.verify(dbRef, Mockito.never()).setValue(Mockito.any(jobModel::class.java))
        Mockito.verify(btnSaveData, Mockito.never()).setOnClickListener(Mockito.any())
        Mockito.verify(btnSaveData, Mockito.never()).performClick()
        Mockito.verify(activity, Mockito.never()).setContentView(Mockito.anyInt())
    }
}