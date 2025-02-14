package com.example.studentsappassignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
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

        adapter?.listener = object: com.example.studentsappassignment.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("TAG", "On click Activity listener on position $position")
            }

            override fun onItemClick(student: Student?) {
                val intent = Intent(this@MainActivity, StudentDetails::class.java)
                intent.putExtra("student", student)
                startActivity(intent)
            }
        }
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
            adapter?.set(it)
            adapter?.notifyDataSetChanged()
    }
}

    }