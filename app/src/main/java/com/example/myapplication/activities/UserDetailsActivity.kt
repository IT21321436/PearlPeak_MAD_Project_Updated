package com.example.myapplication.activities

import com.example.myapplication.models.UserModel
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

import com.google.firebase.database.FirebaseDatabase
class UserDetailsActivity : AppCompatActivity() {

    private lateinit var tvUseId: TextView
    private lateinit var tvUseName: TextView
    private lateinit var tvUseEmail: TextView
    private lateinit var tvUseDescription: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("useId").toString(),
                intent.getStringExtra("useName").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("useId").toString()
            )
        }

    }

    private fun initView() {
        tvUseId = findViewById(R.id.tvUseId)
        tvUseName = findViewById(R.id.tvUseName)
        tvUseEmail = findViewById(R.id.tvUseEmail)
        tvUseDescription = findViewById(R.id.tvUseDescription)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvUseId.text = intent.getStringExtra("useId")
        tvUseName.text = intent.getStringExtra("useName")
        tvUseEmail.text = intent.getStringExtra("useEmail")
        tvUseDescription.text = intent.getStringExtra("useDescription")

    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Users").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "User data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, SupportFetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun openUpdateDialog(
        useId: String,
        useName: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.support_update_dialog, null)

        mDialog.setView(mDialogView)

        val etUseName = mDialogView.findViewById<EditText>(R.id.etUseName)
        val etUseEmail = mDialogView.findViewById<EditText>(R.id.etUseEmail)
        val etUseDescription = mDialogView.findViewById<EditText>(R.id.etUseDescription)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etUseName.setText(intent.getStringExtra("useName").toString())
        etUseEmail.setText(intent.getStringExtra("useEmail").toString())
        etUseDescription.setText(intent.getStringExtra("useDescription").toString())

        mDialog.setTitle("Updating $useName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateUseData(
                useId,
                etUseName.text.toString(),
                etUseEmail.text.toString(),
                etUseDescription.text.toString()
            )

            Toast.makeText(applicationContext, "User Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvUseName.text = etUseName.text.toString()
            tvUseEmail.text = etUseEmail.text.toString()
            tvUseDescription.text = etUseDescription.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateUseData(
        id: String,
        name: String,
        email: String,
        description: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Users").child(id)
        val useInfo = UserModel(id, name, email, description)
        dbRef.setValue(useInfo)
    }

}