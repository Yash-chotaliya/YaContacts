package com.example.yacontacts.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contacts:Contacts)

    @Query("select * from Contact_Table")
    fun getAll():LiveData<List<Contacts>>
}