package com.example.myapplication.activities

import com.example.myapplication.models.UserModel
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SupportInsertionActivity : AppCompatActivity() {

    private lateinit var etUseName: EditText
    private lateinit var etUseEmail: EditText
    private lateinit var etUseDescription: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.support_activity_insertion)

        etUseName = findViewById(R.id.etUseName)
        etUseEmail = findViewById(R.id.etUseEmail)
        etUseDescription = findViewById(R.id.etUseDescription)
        btnSaveData = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        btnSaveData.setOnClickListener {
            saveUserData()
        }
    }

    private fun saveUserData() {

        //getting values
        val useName = etUseName.text.toString()
        val useEmail = etUseEmail.text.toString()
        val useDescription = etUseDescription.text.toString()

        if (useName.isEmpty()) {
            etUseName.error = "Please enter name"
        }
        if (useEmail.isEmpty()) {
            etUseEmail.error = "Please enter email"
        }
        if (useDescription.isEmpty()) {
            etUseDescription.error = "Please enter description"
        }

        val useId = dbRef.push().key!!

        val user = UserModel(useId, useName, useEmail, useDescription)


        dbRef.child(useId).setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etUseName.text.clear()
                etUseEmail.text.clear()
                etUseDescription.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}