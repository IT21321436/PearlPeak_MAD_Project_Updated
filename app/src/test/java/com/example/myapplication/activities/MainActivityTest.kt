//package com.example.myapplication.activities
//
//import android.content.Intent
//import android.widget.Button
//import com.example.myapplication.R
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.*
//import org.mockito.MockitoAnnotations
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class MainActivityTest {
//
//    @Mock
//    private lateinit var mockIntent: Intent
//
//    @Mock
//    private lateinit var mockMainActivity: MainActivity
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//    }
//
//    @Test
//    fun testBtnEmployeeDataClick() {
//        // Given
//        `when`(mockMainActivity.findViewById<Button>(R.id.btnEmployee)).thenReturn(mock(Button::class.java))
//
//        // When
//        mockMainActivity.btnEmployeeData.performClick()
//
//        // Then
//        verify(mockMainActivity).startActivity(mockIntent)
//    }
//
//    @Test
//    fun testBtnEmployerDataClick() {
//        // Given
//        `when`(mockMainActivity.findViewById<Button>(R.id.btnEmployer)).thenReturn(mock(Button::class.java))
//
//        // When
//        mockMainActivity.btnEmployerData.performClick()
//
//        // Then
//        verify(mockMainActivity).startActivity(mockIntent)
//    }
//
//    @Test
//    fun testBtnRatingReviewDataClick() {
//        // Given
//        `when`(mockMainActivity.findViewById<Button>(R.id.btnRatingReview)).thenReturn(mock(Button::class.java))
//
//        // When
//        mockMainActivity.btnRatingReviewData.performClick()
//
//        // Then
//        verify(mockMainActivity).startActivity(mockIntent)
//    }
//
//    @Test
//    fun testBtnContactUsDataClick() {
//        // Given
//        `when`(mockMainActivity.findViewById<Button>(R.id.btnContactUs)).thenReturn(mock(Button::class.java))
//
//        // When
//        mockMainActivity.btnContactUsData.performClick()
//
//        // Then
//        verify(mockMainActivity).startActivity(mockIntent)
//    }
//}
