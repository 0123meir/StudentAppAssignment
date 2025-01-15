package com.example.studentsappassignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsappassignment.model.Model
import com.example.studentsappassignment.model.Student

class EditStudent : AppCompatActivity() {
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameField : EditText = findViewById(R.id.edit_student_editTextName)
        val phoneField : EditText = findViewById(R.id.edit_student_editTextPhone)
        val addressField : EditText = findViewById(R.id.edit_student_editTextPostalAddress)
        val idField: EditText = findViewById(R.id.edit_student_editTextId);
        val checkbox: CheckBox = findViewById(R.id.edit_student_checkbox);
        val cancelButton: Button = findViewById(R.id.edit_student_cancel_button)
        val saveButton: Button = findViewById(R.id.edit_student_save_button)
        val deleteButton: Button = findViewById(R.id.edit_student_delete_button)

        val student = intent.getParcelableExtra<Student>("student")

        nameField.setText(student?.name)
        phoneField.setText(student?.phone)
        addressField.setText(student?.address)
        idField.setText(student?.id)
        checkbox.isChecked = student?.isChecked ?: false

        saveButton.setOnClickListener {
            student?.let { updatedStudent ->
                updatedStudent.id = idField.text.toString()
                updatedStudent.name = nameField.text.toString()
                updatedStudent.phone = phoneField.text.toString()
                updatedStudent.address = addressField.text.toString()
                updatedStudent.picture = R.drawable.default_image
                updatedStudent.isChecked = checkbox.isChecked

                Model.shared.edit(updatedStudent) {
                    returnHome()
                }
            }
        }

        deleteButton.setOnClickListener {
            if (student != null) {
                Model.shared.delete(student) {
                    returnHome()
                }
            }
        }

        cancelButton.setOnClickListener({finish()})
    }

    private fun returnHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}