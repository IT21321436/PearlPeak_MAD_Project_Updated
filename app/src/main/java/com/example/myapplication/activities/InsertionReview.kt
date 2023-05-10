package com.example.myapplication.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.models.ModelReview
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionReview : AppCompatActivity() {

    private lateinit var etReviewName: EditText
    private lateinit var etReviewEmail: EditText
    private lateinit var etReviewTel: EditText
    private lateinit var etReviewRates: EditText
    private lateinit var etReviewComments: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion_review)

        etReviewName = findViewById(R.id.etReviewName)
        etReviewEmail = findViewById(R.id.etReviewEmail)
        etReviewTel = findViewById(R.id.etReviewTel)
        etReviewRates = findViewById(R.id.etReviewRates)
        etReviewComments = findViewById(R.id.etReviewComments)
        btnSaveData = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Review")

        btnSaveData.setOnClickListener {
            saveEmployeeData()
        }
    }

    private fun saveEmployeeData() {
        val reviewName = etReviewName.text.toString()
        val reviewEmail = etReviewEmail.text.toString()
        val reviewTel = etReviewTel.text.toString()
        val reviewRates = etReviewRates.text.toString()
        val reviewComments = etReviewComments.text.toString()

        if (reviewName.isNotEmpty() && reviewEmail.isNotEmpty() && reviewTel.isNotEmpty() && reviewRates.isNotEmpty() && reviewComments.isNotEmpty()) {
            val reviewID = dbRef.push().key!!

            val employee = ModelReview(reviewID, reviewName, reviewEmail, reviewTel, reviewRates, reviewComments)

            dbRef.child(reviewID).setValue(employee)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                    etReviewName.text.clear()
                    etReviewEmail.text.clear()
                    etReviewTel.text.clear()
                    etReviewRates.text.clear()
                    etReviewComments.text.clear()
                }
                .addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        } else {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_LONG).show()
        }
    }
}
