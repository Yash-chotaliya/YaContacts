package com.example.yacontacts.database

import androidx.lifecycle.LiveData

class ContactsRepository(private val contactsDao:ContactsDao) {

    val getAll:LiveData<List<Contacts>> = contactsDao.getAll()

    suspend fun insert(contacts:Contacts){
        contactsDao.insert(contacts)
    }

    suspend fun delete(id:Int){
        contactsDao.delete(id)
    }

}