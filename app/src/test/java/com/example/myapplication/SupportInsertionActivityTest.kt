//package com.example.myapplication
//
//import com.example.myapplication.activities.SupportInsertionActivity
//import com.example.myapplication.models.UserModel
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import org.junit.After
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.MockitoAnnotations
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class SupportInsertionActivityTest {
//    private lateinit var activity: SupportInsertionActivity
//    @Mock
//    private lateinit var dbRef: DatabaseReference
//    @Before
//    fun setup() {
//        MockitoAnnotations.openMocks(this)
//        activity = SupportInsertionActivity()
//        activity.dbRef = dbRef
//    }
//    @After
//    fun tearDown() {
//        Mockito.verifyNoMoreInteractions(dbRef)
//    }
//
//    @Test
//    fun `test saveUserData with valid data`() {
//        // Given
//        val useName = "John"
//        val useEmail = "john@example.com"
//        val useDescription = "This is a test description"
//        val useId = "123"
//        val user = UserModel(useId, useName, useEmail, useDescription)
//        Mockito.`when`(dbRef.push().key).thenReturn(useId)
//        // When
//        activity.saveUserData()
//        // Then
//        Mockito.verify(dbRef).child(useId).setValue(user)
//    }
//    @Test
//    fun `test saveUserData with empty name`() {
//        // Given
//        val useName = ""
//        val useEmail = "john@example.com"
//        val useDescription = "This is a test description"
//        Mockito.`when`(dbRef.push().key).thenReturn("123")
//        // When
//        activity.saveUserData()
//        // Then
//        assertEquals("Please enter name", activity.etUseName.error.toString())
//        Mockito.verifyNoInteractions(dbRef)
//    }
//
//    @Test
//    fun `test saveUserData with empty email`() {
//        // Given
//        val useName = "John"
//        val useEmail = ""
//        val useDescription = "This is a test description"
//        Mockito.`when`(dbRef.push().key).thenReturn("123")
//        // When
//        activity.saveUserData()
//        // Then
//        assertEquals("Please enter email", activity.etUseEmail.error.toString())
//        Mockito.verifyNoInteractions(dbRef)
//    }
//    @Test
//    fun `test saveUserData with empty description`() {
//        // Given
//        val useName = "John"
//        val useEmail = "john@example.com"
//        val useDescription = ""
//        Mockito.`when`(dbRef.push().key).thenReturn("123")
//        // When
//        activity.saveUserData()
//        // Then
//        assertEquals("Please enter description", activity.etUseDescription.error.toString())
//        Mockito.verifyNoInteractions(dbRef)
//    }
//}