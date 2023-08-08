package com.example.room_with_corotines_simplife_task

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {
    //onConflict when make it ignore the the id can duplicate
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insertContact(contact:ContactDataModel)
    @Query("select * from ContactDataModel")
   suspend fun getContacts():List<ContactDataModel>
//   @Query("select id from ContactDataModel")
//   suspend fun getSelectedContact():List<ContactDataModel>

    @Delete()
  suspend  fun delete(contact:ContactDataModel)
    @Update()
  suspend  fun update(contact:ContactDataModel)
}