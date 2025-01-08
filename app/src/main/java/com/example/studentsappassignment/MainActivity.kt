package com.example.studentsappassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsappassignment.model.Model
import com.example.studentsappassignment.model.Student
import com.example.studentsappassignment.model.dao.AppLocalDb

class MainActivity : AppCompatActivity() {

private var students: List<Student>? = null
    private var adapter: StudentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val studentsRecyclerView = findViewById<RecyclerView>(R.id.studentsRecyclerView)
        studentsRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(students)
        studentsRecyclerView.adapter = adapter

        val addStudentButton = findViewById<Button>(R.id.main_activity_add_student_button)
        addStudentButton.setOnClickListener {
            // Create an intent to navigate to AddStudentActivity
            val intent = Intent(this, CreateStudent::class.java)
            startActivity(intent) // Start the AddStudentActivity
        }

    }

    override fun onResume() {
        super.onResume()
        getAllStudents()
    }

    private fun getAllStudents() {

        Model.shared.getAllStudents {
            //TODO: remove when added users
            if (it.isEmpty()) {
                this.students = listOf(
                    Student(
                        "1",
                        "Alice",
                        "123-456-7890",
                        "123 Main St",
                        R.drawable.default_image,
                        isChecked = true
                    ),
                    Student(
                        "2",
                        "Bob",
                        "987-654-3210",
                        "456 Oak Ave",
                        R.drawable.ic_launcher_foreground,
                        isChecked = false
                    ),
                    Student(
                        "3",
                        "Charlie",
                        "555-123-4567",
                        "789 Elm St",
                        R.drawable.default_image,
                        isChecked = true
                    )
                )
                adapter?.set(this.students)
            } else {
            this.students = it
            adapter?.set(it)
            }
            adapter?.notifyDataSetChanged()
    }
}

    }