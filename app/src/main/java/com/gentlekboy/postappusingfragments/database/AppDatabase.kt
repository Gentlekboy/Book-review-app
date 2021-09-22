package com.gentlekboy.postappusingfragments.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gentlekboy.postappusingfragments.model.comments.CommentListItem
import com.gentlekboy.postappusingfragments.model.posts.PostListItem

//Signify tables by using the @Database entity property
@Database(entities = [PostListItem::class, CommentListItem::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDaoInstance(): AppDao

    //Create a single instance of database
    companion object{
        private var dbInstance: AppDatabase? = null

        fun getDbInstance(context: Context): AppDatabase{
            if (dbInstance == null){
                dbInstance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "postDb")
                    .allowMainThreadQueries()
                    .build()
            }

            return dbInstance!!
        }
    }
}