package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.myapplication.R

class CategorySelection : AppCompatActivity() {
    private lateinit var ivivHelth: ImageView
    private lateinit var ivEdu: ImageView
    private lateinit var ivIT: ImageView
    private lateinit var ivArtTechnology: ImageView
    private lateinit var ivArchitecture: ImageView
    private lateinit var ivBusiness: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categories)

        //val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        ivivHelth = findViewById(R.id.ivHelth)
        ivEdu = findViewById(R.id.ivEdu)
        ivIT = findViewById(R.id.ivIT)
        ivArtTechnology = findViewById(R.id.ivArtTechnology)
        ivArchitecture = findViewById(R.id.ivArchitecture)
        ivBusiness = findViewById(R.id.ivBusiness)

        ivivHelth.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        ivEdu.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        ivIT.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        ivArtTechnology.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        ivArchitecture.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        ivBusiness.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}