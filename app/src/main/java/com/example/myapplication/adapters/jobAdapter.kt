package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.EmployeeModel
import com.example.myapplication.models.JobData
import com.example.myapplication.models.jobModel

class jobAdapter(private val jobList : ArrayList<jobModel>) :
    RecyclerView.Adapter<jobAdapter.ViewHolder>(){

    private lateinit var mListner: onItemClickListener
    //private lateinit var jobData: Number

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListner = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.posted_job_card, parent, false)
        return ViewHolder(itemView,mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCompany = jobList[position]
        holder.tvCompanyName.text = currentCompany.CompanyName
        //holder.tvCompanyCount.text = jobList.size.toString()
    }

    override fun getItemCount(): Int {
        //val jobData = JobData(jobList.size.toString())
        return jobList.size
    }

    class ViewHolder (itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvCompanyName : TextView = itemView.findViewById(R.id.tvCompanyName)
        //val tvCompanyCount : TextView = itemView.findViewById(R.id.tvCount)

        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }



}