package com.gentlekboy.postappusingfragments.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gentlekboy.postappusingfragments.model.PostListItem

@Database(entities = [PostListItem::class], version = 1, exportSchema = false)
abstract class PostDatabase: RoomDatabase() {
    abstract fun getPostDao(): PostDao

    companion object{
        private var dbInstance: PostDatabase? = null

        fun getDbInstance(context: Context): PostDatabase{
            if (dbInstance == null){
                dbInstance = Room.databaseBuilder(context.applicationContext, PostDatabase::class.java, "postDb")
                    .allowMainThreadQueries()
                    .build()
            }

            return dbInstance!!
        }
    }
}