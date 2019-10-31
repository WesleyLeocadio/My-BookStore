package com.example.mybookstore.connectionDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mybookstore.dao.BookDao
import com.example.mybookstore.domain.Book

@Database (entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao



}