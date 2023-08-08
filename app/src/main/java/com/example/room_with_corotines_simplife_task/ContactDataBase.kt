package com.example.room_with_corotines_simplife_task

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactDataModel::class], version = 1, exportSchema = false)
abstract class ContactDataBase : RoomDatabase() {

    abstract val contactDao:ContactDao
    companion object {
        private lateinit var instance: ContactDataBase
        fun getInstance(context: Context):ContactDataBase {
            synchronized(ContactDataBase::class.java) {
                if (!::instance.isInitialized) {
                    instance =
                        Room.databaseBuilder(context, ContactDataBase::class.java, "contact_db")
                            .fallbackToDestructiveMigrationOnDowngrade()
                            .fallbackToDestructiveMigration().allowMainThreadQueries()
                            .build()
                }
            }
            return instance
        }
    }

}