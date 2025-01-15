package com.example.studentsappassignment.model

import android.os.Looper
import androidx.core.os.HandlerCompat
import com.example.studentsappassignment.model.dao.AppLocalDb
import com.example.studentsappassignment.model.dao.AppLocalDbRepository
import java.util.concurrent.Executors

typealias StudentsCallback = (List<Student>) -> Unit
typealias StudentCallback = (Student) -> Unit

typealias EmptyCallback = () -> Unit

interface GetAllStudentsListener {
    fun onCompletion(students: List<Student>)
}

class Model private constructor() {

    private val database: AppLocalDbRepository = AppLocalDb.database
    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())

    companion object {
        val shared = Model()
    }
    fun getStudentById(id: String, callback: StudentCallback) {
        executor.execute {
            val student = database.studentDao().getStudentById(id)

            mainHandler.post {
                callback(student)
            }
        }
    }

    fun getAllStudents(callback: StudentsCallback) {
        executor.execute {
            val students = database.studentDao().getAllStudents()

            mainHandler.post {
                callback(students)
            }
        }
    }
    fun add(student: Student, callback: EmptyCallback) {
        executor.execute {
            database.studentDao().insertStudents(student)

            mainHandler.post {
                callback()
            }
        }
    }

    fun edit(student: Student, callback: (Student) -> Unit) {
        executor.execute {
            database.studentDao().updateStudent(student)

            mainHandler.post {
                callback(student)
            }
        }
    }

    fun delete(student: Student, callback: EmptyCallback) {
        executor.execute {
            database.studentDao().delete(student)

            mainHandler.post {
                callback()
            }
        }
    }
}
