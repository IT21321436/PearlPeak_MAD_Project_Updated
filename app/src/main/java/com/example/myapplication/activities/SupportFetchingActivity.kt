package com.example.myapplication.activities

import com.example.myapplication.models.UserModel
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication1.UseAdapter
import com.google.firebase.database.*

class SupportFetchingActivity : AppCompatActivity() {

    private lateinit var useRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var useList: ArrayList<UserModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.support_activity_fetching)

        useRecyclerView = findViewById(R.id.rvUse)
        useRecyclerView.layoutManager = LinearLayoutManager(this)
        useRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        useList = arrayListOf<UserModel>()

        getUsersData()

    }

    private fun getUsersData() {

        useRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                useList.clear()
                if (snapshot.exists()){
                    for (useSnap in snapshot.children){
                        val useData = useSnap.getValue(UserModel::class.java)
                        useList.add(useData!!)
                    }
                    val mAdapter = UseAdapter(useList)
                    useRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : UseAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@SupportFetchingActivity, UserDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("useId", useList[position].useId)
                            intent.putExtra("useName", useList[position].useName)
                            intent.putExtra("useEmail", useList[position].useEmail)
                            intent.putExtra("useDescription", useList[position].useDescription)
                            startActivity(intent)
                        }

                    })

                    useRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}

