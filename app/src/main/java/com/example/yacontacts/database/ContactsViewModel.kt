package com.example.yacontacts.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel(application: Application):AndroidViewModel(application) {

    val getAll : LiveData<List<Contacts>>

    private val repository :ContactsRepository

    init {
        val contactsDao = ContactsDatabase.getInstance(application).getContactsDao()
        repository = ContactsRepository(contactsDao)
        getAll = repository.getAll
    }

    fun insert(contacts:Contacts){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(contacts)
        }
    }
}