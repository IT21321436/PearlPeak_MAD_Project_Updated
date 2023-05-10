package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast


import com.example.myapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private  lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginbtn.setOnClickListener {
            val email = binding.username.text.toString()
            val password = binding.loginPassword.text.toString()

            if (email.isNotEmpty()&& password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, CategorySelection::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }else {
                Toast.makeText(this,"Fields cannot be Empty",Toast.LENGTH_SHORT).show()
            }

        }
        binding.Register.setOnClickListener{
            val signupIntent = Intent(this, RegisterActivity::class.java)
            startActivity(signupIntent)
        }
    }
}