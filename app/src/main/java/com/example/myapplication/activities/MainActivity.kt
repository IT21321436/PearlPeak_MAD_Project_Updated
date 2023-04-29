package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var btnInsertData: Button
    private lateinit var btnFetchData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.posted_jobs)

        //val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnInsertData = findViewById(R.id.btnJobPost)
        btnFetchData = findViewById(R.id.btnJobData)

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