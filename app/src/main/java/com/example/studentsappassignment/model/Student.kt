package com.example.studentsappassignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey val id: String,
    val name: String,
    val phone: String,
    val address: String,
    val picture: Int,
    val isChecked: Boolean
)