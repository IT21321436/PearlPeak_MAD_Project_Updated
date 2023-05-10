package com.example.myapplication.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
//import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.models.jobModel
import com.google.firebase.database.FirebaseDatabase

class postedDetailsActivity : AppCompatActivity(){

    private lateinit var tvCompanyId: TextView
    private lateinit var tvCompanyName: TextView
    private lateinit var tvCompanyEmail: TextView
    private lateinit var tvCompanyPhone: TextView
    private lateinit var tvCompanyCategory: TextView
    private lateinit var tvCompanyDiscription: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.posted_job_details)

        initView()
        setValuesToView()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("companyId").toString(),
                intent.getStringExtra("companyName").toString()

            )
        }
        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("companyId").toString()
            )
        }

    }

    private fun initView() {

        tvCompanyId = findViewById(R.id.tvCompanyId)
        tvCompanyName = findViewById(R.id.tvCompanyName)
        tvCompanyEmail = findViewById(R.id.tvEmail)
        tvCompanyPhone = findViewById(R.id.tvCompanyPhone)
        tvCompanyCategory = findViewById(R.id.tvCompanyCategory)
        tvCompanyDiscription = findViewById(R.id.tvCompanyDiscription)


        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToView(){

        tvCompanyId.text = intent.getStringExtra("companyId")
        tvCompanyName.text = intent.getStringExtra("companyName")
        tvCompanyEmail.text = intent.getStringExtra("companyEmail")
        tvCompanyPhone.text = intent.getStringExtra("companyPhone")
        tvCompanyCategory.text = intent.getStringExtra("companyCategory")
        tvCompanyDiscription.text = intent.getStringExtra("companyDiscription")


    }


    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("JobPortal").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Company data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, jobFetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun openUpdateDialog(
        companyId: String,
        companyName: String
    ){

        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_posted_jobs, null)

        mDialog.setView(mDialogView)

        val etCompanyName = mDialogView.findViewById<EditText>(R.id.etCompanyName)
        val etCompanyEmail = mDialogView.findViewById<EditText>(R.id.etCompanyEmail)
        val etCompanyPhone = mDialogView.findViewById<EditText>(R.id.etPhone)
        val etCompanyCategory = mDialogView.findViewById<EditText>(R.id.etCategory)
        val etCompanyDiscription = mDialogView.findViewById<EditText>(R.id.etCompanyDiscription)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnSubmit)

        etCompanyName.setText(intent.getStringExtra("companyName").toString())
        etCompanyEmail.setText(intent.getStringExtra("companyEmail").toString())
        etCompanyPhone.setText(intent.getStringExtra("companyPhone").toString())
        etCompanyPhone.setText(intent.getStringExtra("companyCategory").toString())
        etCompanyDiscription.setText(intent.getStringExtra("companyDiscription").toString())

        mDialog.setTitle("Updating $companyName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateUserData(
                companyId,
                etCompanyName.text.toString(),
                etCompanyEmail.text.toString(),
                etCompanyPhone.text.toString(),
                etCompanyCategory.text.toString(),
                etCompanyDiscription.text.toString()

            )

            Toast.makeText(applicationContext, "User Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvCompanyName.text = etCompanyName.text.toString()
            tvCompanyEmail.text = etCompanyEmail.text.toString()
            tvCompanyPhone.text = etCompanyPhone.text.toString()
            tvCompanyCategory.text = etCompanyCategory.text.toString()
            tvCompanyDiscription.text = etCompanyDiscription.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updateUserData(
        id: String,
        name: String,
        email: String,
        phone: String,
        category: String,
        discription: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("JobPortal").child(id)
        val userInfo = jobModel(id, name, email, phone,category,discription)
        dbRef.setValue(userInfo)
    }
}