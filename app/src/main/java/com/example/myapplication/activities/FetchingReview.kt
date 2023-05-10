package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.AdapterReview
import com.example.myapplication.models.ModelReview
import com.google.firebase.database.*

class FetchingReview : AppCompatActivity() {

    private lateinit var revRecyclerview: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var revList: ArrayList<ModelReview>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching_review)

        revRecyclerview = findViewById(R.id.rvEmp)
        revRecyclerview.layoutManager = LinearLayoutManager(this)
        revRecyclerview.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        revList = arrayListOf<ModelReview>()

        getReviewData()

    }

    private fun getReviewData() {

        revRecyclerview.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Review")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                revList.clear()
                if (snapshot.exists()){
                    for (reviewSnap in snapshot.children){
                        val revData = reviewSnap.getValue(ModelReview::class.java)
                        revList.add(revData!!)
                    }
                    val mAdapter = AdapterReview(revList)
                    revRecyclerview.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : AdapterReview.onItemClickListener {
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@FetchingReview, ReviewDetails::class.java)

                            //put extras
                            intent.putExtra("reviewID", revList[position].reviewID)
                            intent.putExtra("reviewName", revList[position].reviewName)
                            intent.putExtra("reviewEmail", revList[position].reviewEmail)
                            intent.putExtra("reviewTel", revList[position].reviewTel)
                            intent.putExtra("reviewComments", revList[position].reviewComments)
                            intent.putExtra("reviewRates", revList[position].reviewRates)

                            startActivity(intent)
                        }

                    })

                    revRecyclerview.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}

