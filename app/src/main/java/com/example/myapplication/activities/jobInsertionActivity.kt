package com.example.myapplication.activities

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.models.jobModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class jobInsertionActivity : AppCompatActivity() {

    private lateinit var companyName: EditText
    private lateinit var companyEmail: EditText
    private lateinit var companyPhone: EditText
    private lateinit var companyCategory: Spinner
    private lateinit var companyDiscription: EditText
    private lateinit var jobCheckBox: CheckBox
    private lateinit var btnSaveData: Button

    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jobposting)

        companyName = findViewById(R.id.editCompanyName)
        companyEmail = findViewById(R.id.editCompanyEmail)
        companyPhone = findViewById(R.id.editPhone)
        companyCategory = findViewById(R.id.editCategory)
        companyDiscription = findViewById(R.id.editCompanyDiscription)
        jobCheckBox = findViewById(R.id.companyCheckBox)
        btnSaveData = findViewById(R.id.btnSubmit)

        dbRef = FirebaseDatabase.getInstance().getReference("JobPortal")

        btnSaveData.setOnClickListener{
            saveCompanyData()
        }



    }

    private fun saveCompanyData() {
        //getting Values
        val CompanyName = companyName.text.toString()
        val Email = companyEmail.text.toString()
        val CompanyPhone = companyPhone.toString()
        val CompanyCategory = companyCategory.toString()
        val CompanyDiscription = companyDiscription.text.toString()
        val termsAgreed = jobCheckBox.isChecked

//        val MIN_PHONE_NUMBER = 1000000000
//        val MAX_PHONE_NUMBER = 9999999999

        if (CompanyName.isEmpty()){
            companyName.error = "Please Enter User Name"
        }
        if (Email.isEmpty()) {
            companyEmail.error = "Please Enter Email Address"
        }

//        if (CompanyPhone < MIN_PHONE_NUMBER || CompanyPhone > MAX_PHONE_NUMBER) {
//            companyPhone.error = "Please enter a phone number between $MIN_PHONE_NUMBER and $MAX_PHONE_NUMBER"
//        }

//        if (CompanyCategory.isEmpty()) {
//            companyCategory.error = "Please Enter Category"
//        }

        if (CompanyDiscription.isEmpty()) {
            companyDiscription.error = "Please Enter Description"
        }

        if (!termsAgreed) {
            jobCheckBox.error = "Please Agree to the Terms and Conditions"
        }

        val uId = dbRef.push().key!!

        val Job = jobModel(uId,CompanyName,Email,CompanyPhone,CompanyCategory,CompanyDiscription)

        dbRef.child(uId).setValue(Job)
            .addOnCompleteListener {
                Toast.makeText(this,"Data Inserted Successsfully", Toast.LENGTH_LONG).show()

                companyName.text.clear()
                companyEmail.text.clear()
                companyPhone.text.clear()
//              companyCategory.text.clear()
                companyDiscription.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}