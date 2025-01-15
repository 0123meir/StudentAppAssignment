package com.example.studentsappassignment

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsappassignment.model.Model
import com.example.studentsappassignment.model.Student

interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onItemClick(student: Student?)
}
class StudentAdapter(private var studentList: List<Student>?) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    var listener: OnItemClickListener? = null

    fun set(students: List<Student>?) {
        this.studentList = students
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false) // Create your student_item layout
        return StudentViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList?.get(position), position)
    }

    class StudentViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.student_name)
        val idTextView: TextView = itemView.findViewById(R.id.student_id)
        val pictureImageView: ImageView = itemView.findViewById(R.id.student_image)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        private var student: Student? = null
        init {
            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
                listener?.onItemClick(student)
            }
        }

        fun bind(student: Student?, position: Int) {
            this.student = student
            nameTextView.text = student?.name
            idTextView.text = student?.id
            checkBox.apply {
                isChecked = student?.isChecked ?: false
                tag = position
            }
        }
    }
    
    override fun getItemCount(): Int {
        return studentList?.size ?: 0
    }
}