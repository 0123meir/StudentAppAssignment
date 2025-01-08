package com.example.studentsappassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsappassignment.model.Student

class StudentAdapter(private var studentList: List<Student>?) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.student_name)
        val idTextView: TextView = itemView.findViewById(R.id.student_id)
        val pictureImageView: ImageView = itemView.findViewById(R.id.student_image)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
//                    val clickedStudent = studentList[position]
//                    Handle item click here
                }

            }
        }

    }

    fun set(students: List<Student>?) {
        this.studentList = students
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false) // Create your student_item layout
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val currentStudent = studentList?.get(position)
            holder.nameTextView.text = currentStudent?.name
            holder.idTextView.text = currentStudent?.id
            holder.pictureImageView.setImageResource(currentStudent?.picture ?: R.drawable.default_image)
            holder.checkBox.isChecked = currentStudent?.isChecked ?: false
    }
    
    override fun getItemCount(): Int {
        return studentList?.size ?: 0
    }
}