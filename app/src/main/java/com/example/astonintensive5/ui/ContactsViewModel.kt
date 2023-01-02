package com.example.astonintensive5.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astonintensive5.data.ContactsDataMock
import com.example.astonintensive5.model.Contact

class ContactsViewModel : ViewModel() {
    private var _contactsData: MutableLiveData<ArrayList<Contact>> =
        MutableLiveData<ArrayList<Contact>>(arrayListOf())
    val contactsData: LiveData<ArrayList<Contact>>
        get() = _contactsData

    private var _currentContact: MutableLiveData<Contact> = MutableLiveData<Contact>()
    val currentContact: LiveData<Contact>
        get() = _currentContact

    private var _isContactSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    val isContactSelected: LiveData<Boolean> = _isContactSelected

    init {
        _contactsData.value = ContactsDataMock.getStartData()
    }

    fun onSelectedContactStateChange(isSelected: Boolean) {
        _isContactSelected.value = isSelected
    }

    fun onCurrentContactChange(contact: Contact) {
        _currentContact.value = contact
    }

    fun onContactDataChange(contact: Contact) {
        val currentContactsData = contactsData.value
        if (currentContactsData != null) {
            currentContactsData[contact.id] = contact
            _contactsData.value = currentContactsData ?: arrayListOf()
        }
    }
}