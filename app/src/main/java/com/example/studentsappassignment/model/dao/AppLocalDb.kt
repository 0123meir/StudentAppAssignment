package com.example.studentsappassignment.model.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studentsappassignment.base.MyApplication
import com.example.studentsappassignment.model.Student
import com.example.studentsappassignment.model.StudentDao

@Database(entities = [Student::class], version = 2)
abstract class AppLocalDbRepository: RoomDatabase() {
    abstract fun studentDao(): StudentDao
}

object AppLocalDb {

    val database: AppLocalDbRepository by lazy {

        val context = MyApplication.Globals.context ?: throw IllegalStateException("Application context is missing")

        Room.databaseBuilder(
            context = context,
            klass = AppLocalDbRepository::class.java,
            name = "dbFile.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}