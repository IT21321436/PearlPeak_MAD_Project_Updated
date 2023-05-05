package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R

class JobMainActivity : AppCompatActivity() {
    private lateinit var btnInsertData: Button
    private lateinit var btnFetchData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_selection)

        //val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnInsertData = findViewById(R.id.btnJobInsertData)
        btnFetchData = findViewById(R.id.btnJobFetchData)

        btnInsertData.setOnClickListener {
            val intent = Intent(this, jobInsertionActivity::class.java)
            startActivity(intent)
        }
        btnFetchData.setOnClickListener{
            val intent = Intent(this, jobFetchingActivity::class.java)
            startActivity(intent)
        }
    }
}