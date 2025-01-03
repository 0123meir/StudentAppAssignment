package com.example.studentsappassignment

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.student_item)

//            val recyclerView: androidx.recyclerview.widget.RecyclerView = findViewById(R.id.student_recycler_view)
//            recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

            val studentList = listOf(
                Student(1, "Alice", "123-456-7890", "123 Main St", R.drawable.default_image),
                Student(2, "Bob", "987-654-3210", "456 Oak Ave", R.drawable.default_image),
                // Add more students here
            )

//            val adapter = StudentAdapter(studentList)
//            recyclerView.adapter = adapter
        }
}
