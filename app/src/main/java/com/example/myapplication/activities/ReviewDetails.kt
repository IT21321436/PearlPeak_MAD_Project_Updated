package com.example.myapplication.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.models.ModelReview
import com.google.firebase.database.FirebaseDatabase

class ReviewDetails : AppCompatActivity(){

    private lateinit var tvReviewId: TextView
    private lateinit var tvReviewName: TextView
    private lateinit var tvReviewEmail: TextView
    private lateinit var tvReviewTel: TextView
    private lateinit var tvReviewRates: TextView
    private lateinit var tvReviewComments: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_details)

        initView()
        setValuesToViews()

        //Update Btn
        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("reviewID").toString(),
                intent.getStringExtra("reviewName").toString()
            )
        }


        //Delete btn
        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("reviewID").toString()    //get unique ID
            )
        }

    }

    private fun deleteRecord(id: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Review").child(id)

        AlertDialog.Builder(this)
            .setTitle("Delete Review")
            .setMessage("Are you sure you want to delete this review record?")
            .setPositiveButton("Yes") { _, _ ->
                val mTask = dbRef.removeValue()
                mTask.addOnSuccessListener {
                    Toast.makeText(this, "Review data deleted", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, FetchingReview::class.java)
                    finish()
                    startActivity(intent)
                }.addOnFailureListener { error ->
                    Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
                }
            }
            .setNegativeButton("No", null)
            .show()
    }


    private fun initView() {
        tvReviewId = findViewById(R.id.tvReviewId)
        tvReviewName = findViewById(R.id.tvReviewName)
        tvReviewEmail = findViewById(R.id.tvReviewEmail)
        tvReviewTel = findViewById(R.id.tvReviewTel)
        tvReviewRates = findViewById(R.id.tvReviewRates)
        tvReviewComments = findViewById(R.id.tvReviewComments)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvReviewId.text = intent.getStringExtra("reviewID")
        tvReviewName.text = intent.getStringExtra("reviewName")
        tvReviewEmail.text = intent.getStringExtra("reviewEmail")
        tvReviewTel.text = intent.getStringExtra("reviewTel")
        tvReviewRates.text = intent.getStringExtra("reviewRates")
        tvReviewComments.text = intent.getStringExtra("reviewComments")


    }

    @SuppressLint("MissingInflatedId")
    private fun openUpdateDialog(
        reviewID: String,
        reviewName: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.review_updatedialog, null)

        mDialog.setView(mDialogView)

        val etReviewName = mDialogView.findViewById<EditText>(R.id.etReviewName)
        val etReviewEmail = mDialogView.findViewById<EditText>(R.id.etReviewEmail)
        val etReviewTel = mDialogView.findViewById<EditText>(R.id.etReviewTel)
        val etReviewRates = mDialogView.findViewById<EditText>(R.id.etReviewRates)
        val etReviewComments = mDialogView.findViewById<EditText>(R.id.etReviewComments)


        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        //Set data to edit text
        etReviewName.setText(intent.getStringExtra("reviewName").toString())
        etReviewEmail.setText(intent.getStringExtra("reviewEmail").toString())
        etReviewTel.setText(intent.getStringExtra("reviewTel").toString())
        etReviewRates.setText(intent.getStringExtra("reviewRates").toString())
        etReviewComments.setText(intent.getStringExtra("reviewComments").toString())

        mDialog.setTitle("Updating $reviewName Record")     //which user data is updating

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateReviewData(            //recording updated data
                reviewID,
                etReviewName.text.toString(),
                etReviewEmail.text.toString(),
                etReviewTel.text.toString(),
                etReviewRates.text.toString(),
                etReviewComments.text.toString()

            )

            Toast.makeText(applicationContext, "Review Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated new data to our text views
            tvReviewName.text = etReviewName.text.toString()
            tvReviewEmail.text = etReviewEmail.text.toString()
            tvReviewTel.text = etReviewTel.text.toString()
            tvReviewRates.text = etReviewRates.text.toString()
            tvReviewComments.text = etReviewComments.text.toString()


            alertDialog.dismiss()   //clear alert
        }


    }

    private fun updateReviewData(
        id: String,
        name: String,
        email: String,
        tel: String,
        rate: String,
        comment: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Review").child(id)      //get data by Review id
        val revInfo = ModelReview(id, name, email, tel, rate, comment)
        dbRef.setValue(revInfo)
    }

}

