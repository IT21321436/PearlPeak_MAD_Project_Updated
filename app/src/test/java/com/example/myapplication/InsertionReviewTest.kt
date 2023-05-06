//package com.example.myapplication
//
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.example.myapplication.activities.InsertionReview
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import org.mockito.Mock
//import org.mockito.Mockito.*
//import org.mockito.MockitoAnnotations
//
//
//class InsertionReviewTest {
//    private lateinit var activity: InsertionReview
//    private lateinit var etReviewName: EditText
//    private lateinit var etReviewEmail: EditText
//    private lateinit var etReviewTel: EditText
//    private lateinit var etReviewRates: EditText
//    private lateinit var etReviewComments: EditText
//    private lateinit var btnSaveData: Button
//    @Mock
//    private lateinit var dbRef: DatabaseReference
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.openMocks(this)
//        activity = InsertionReview()
//        etReviewName = EditText(activity)
//        etReviewEmail = EditText(activity)
//        etReviewTel = EditText(activity)
//        etReviewRates = EditText(activity)
//        etReviewComments = EditText(activity)
//        btnSaveData = Button(activity)
//        activity.dbRef = dbRef
//        etReviewName.setText("Test name")
//        etReviewEmail.setText("test@email.com")
//        etReviewTel.setText("1234567890")
//        etReviewRates.setText("5")
//        etReviewComments.setText("Test comment")
//    }
//
//    @Test
//    fun saveEmployeeData_validInput_dataInsertedSuccessfully() {
//        `when`(dbRef.push().key).thenReturn("123")
//        val modelReview = ModelReview("123", "Test name", "test@email.com", "1234567890", "5", "Test
//                comment")
//                doAnswer { invocation ->
//            val listener = invocation.arguments[0] as DatabaseReference.CompletionListener
//            listener.onComplete(null, dbRef)
//            null
//        }.`when`(dbRef).child("123").setValue(modelReview)
//                activity.saveEmployeeData()
//                verify(dbRef).child("123").setValue(modelReview)
//                verify(etReviewName).text.clear()
//                verify(etReviewEmail).text.clear()
//                verify(etReviewTel).text.clear()
//                verify(etReviewRates).text.clear()
//                verify(etReviewComments).text.clear()
//                verifyToastMessageDisplayed("Data inserted successfully")
//    }
//    @Test
//    fun saveEmployeeData_databaseError_errorToastDisplayed() {
//        `when`(dbRef.push().key).thenReturn("123")
//        val modelReview = ModelReview("123", "Test name", "test@email.com", "1234567890", "5",
//            "Test comment")
//        doAnswer { invocation ->
//            val listener = invocation.arguments[0] as DatabaseReference.CompletionListener
//            listener.onComplete(DatabaseError.fromException(Exception("Test error")), dbRef)
//            null
//        }.`when`(dbRef).child("123").setValue(modelReview)
//        activity.saveEmployeeData()
//        verify(dbRef).child("123").setValue(modelReview)
//        verifyZeroInteractions(etReviewName, etReviewEmail, etReviewTel, etReviewRates,
//            etReviewComments)
//        verifyToastMessageDisplayed("Error Test error")
//    }
//}