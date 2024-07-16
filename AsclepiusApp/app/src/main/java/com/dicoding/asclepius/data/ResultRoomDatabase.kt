package com.dicoding.asclepius.data

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.asclepius.data.Result

@Database(entities = [Result::class], version = 1)
abstract class ResultRoomDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultDao

    companion object {
        @Volatile
        private var instance: ResultRoomDatabase? = null

        fun getInstance(context: Context): ResultRoomDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    ResultRoomDatabase::class.java, "result.db"
                ).build()
            }
    }
}