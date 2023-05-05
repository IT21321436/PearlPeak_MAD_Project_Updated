package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    private lateinit var btnEmployeeData: Button
    private lateinit var btnEmployerData: Button
    private lateinit var btnRatingReviewData: Button
    private lateinit var btnContactUsData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        //val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnEmployeeData = findViewById(R.id.btnEmployee)
        btnEmployerData = findViewById(R.id.btnEmployer)
        btnRatingReviewData = findViewById(R.id.btnRatingReview)
        btnContactUsData = findViewById(R.id.btnContactUs)

        btnEmployeeData.setOnClickListener {
            val intent = Intent(this, SelectionActivity::class.java)
            startActivity(intent)
        }
        btnEmployerData.setOnClickListener{
            val intent = Intent(this, JobMainActivity::class.java)
            startActivity(intent)
        }
        btnRatingReviewData.setOnClickListener{
            val intent = Intent(this, JobMainActivity::class.java)
            startActivity(intent)
        }
        btnContactUsData.setOnClickListener{
            val intent = Intent(this, JobMainActivity::class.java)
            startActivity(intent)
        }
    }
}