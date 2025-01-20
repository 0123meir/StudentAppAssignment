package com.example.studentsappassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsappassignment.model.Model
import com.example.studentsappassignment.model.Student

class CreateStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameField : EditText = findViewById(R.id.editTextName)
        val phoneField : EditText = findViewById(R.id.editTextPhone)
        val addressField : EditText = findViewById(R.id.editTextPostalAddress)
        val idField: EditText = findViewById(R.id.editTextId)
        val checkbox: CheckBox = findViewById(R.id.checkbox)
        val cancelButton: Button = findViewById(R.id.create_student_cancel_button)
        val saveButton: Button = findViewById(R.id.create_student_save_button)

        saveButton.setOnClickListener {
            val newId = idField.text.toString()

            if (newId.isBlank()) {
                Toast.makeText(this, "ID cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Model.shared.getStudentById(newId) { existingStudent ->
                if (existingStudent != null) {
                    Toast.makeText(this, "Student with ID $newId already exists.", Toast.LENGTH_SHORT).show()
                } else {
                    val student = Student(
                        id = newId,
                        name = nameField.text.toString(),
                        phone = phoneField.text.toString(),
                        address = addressField.text.toString(),
                        picture = R.drawable.default_image,
                        isChecked = checkbox.isChecked
                    )

                    Model.shared.add(student) {
                        finish()
                    }
                }
            }
        }


        cancelButton.setOnClickListener({finish()})
    }
}