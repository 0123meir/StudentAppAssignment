package com.example.studentsappassignment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val mockStudents = listOf(
        Student(1, "Alice", "123-456-7890", "123 Main St", R.drawable.default_image),
        Student(2, "Bob", "987-654-3210", "456 Oak Ave", R.drawable.ic_launcher_foreground),
        Student(3, "Charlie", "555-123-4567", "789 Elm St", R.drawable.default_image)
    )

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

        studentsRecyclerView.adapter = StudentAdapter(mockStudents)

    }
}