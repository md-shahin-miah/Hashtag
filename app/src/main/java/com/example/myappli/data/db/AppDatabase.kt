package com.example.myappli.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myappli.model.Category
import com.example.myappli.model.Tag
import com.example.myappli.data.db.dao.CategoryDAO
import com.example.myappli.data.db.dao.TagDAO

@Database(
    entities = [Category::class, Tag::class],
    version = 1
)


abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDAO(): CategoryDAO
    abstract fun tagDAO(): TagDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        context.applicationContext.packageName + ".DB"
                    ).build()
                }
            }
            return INSTANCE
        }
        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
