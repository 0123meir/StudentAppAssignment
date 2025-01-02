package com.example.studentsappassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.semantics.text

class StudentAdapter(private val studentList: List<Student>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.student_name)
        val idTextView: TextView = itemView.findViewById(R.id.student_id)
        val phoneTextView: TextView = itemView.findViewById(R.id.student_phone)
        val addressTextView: TextView = itemView.findViewById(R.id.student_address)
        val pictureImageView: ImageView = itemView.findViewById(R.id.student_picture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false) // Create your student_item layout
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val currentStudent = studentList[position]
        holder.nameTextView.text = currentStudent.name
        holder.idTextView.text = currentStudent.id.toString()
        holder.phoneTextView.text = currentStudent.phone
        holder.addressTextView.text = currentStudent.address
        holder.pictureImageView.setImageResource(currentStudent.picture)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}