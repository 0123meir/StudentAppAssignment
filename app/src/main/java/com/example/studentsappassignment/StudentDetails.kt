package com.example.studentsappassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsappassignment.model.Model
import com.example.studentsappassignment.model.Student

class StudentDetails : AppCompatActivity() {
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name : TextView = findViewById(R.id.textName)
        val phone : TextView = findViewById(R.id.textPhone)
        val address : TextView = findViewById(R.id.textPostalAddress)
        val id: TextView = findViewById(R.id.student_details_textId)
        val checkbox: CheckBox = findViewById(R.id.student_details_checkbox)
        val editButton: Button = findViewById(R.id.student_details_edit_button)

        val student = intent.getParcelableExtra<Student>("student")

            name.text = student?.name
            phone.text = student?.phone
            address.text = student?.address
            id.text = student?.id
            checkbox.isChecked = student?.isChecked ?: false
            editButton.setOnClickListener{
                val intent = Intent(this, EditStudent::class.java)
                intent.putExtra("studentId", it.id)
                startActivity(intent)
            }

   }
}