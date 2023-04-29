package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.jobAdapter
import com.example.myapplication.models.jobModel
import com.google.firebase.database.*

class jobFetchingActivity : AppCompatActivity() {
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var jobList: ArrayList<jobModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.posted_jobs)

        jobRecyclerView = findViewById(R.id.rvUser)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        jobList = arrayListOf<jobModel>()

        getUserData()
    }

    private fun getUserData() {
        jobRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("JobPortal")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                jobList.clear()
                if (snapshot.exists()){
                    for(jobsnap in snapshot.children) {
                        val jobData = jobsnap.getValue(jobModel::class.java)
                        jobList.add(jobData!!)
                    }
                    val mAdapter = jobAdapter(jobList)
                    jobRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : jobAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@jobFetchingActivity, postedDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("companyId", jobList[position].CompanyId)
                            intent.putExtra("companyName", jobList[position].CompanyName)
                            intent.putExtra("companyEmail", jobList[position].CompanyEmail)
                            intent.putExtra("companyPhone", jobList[position].CompanyPhone)
                            intent.putExtra("companyCategory", jobList[position].CompanyCategory)
                            intent.putExtra("companyDiscription", jobList[position].CompanyDiscription)
                            startActivity(intent)

                        }
                    })

                    jobRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}