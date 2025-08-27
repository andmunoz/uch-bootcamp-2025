package cl.uchile.postgrado.mobile.contactlist.ui.screens.contactlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.uchile.postgrado.mobile.contactlist.model.room.ContactRepository
import cl.uchile.postgrado.mobile.contactlist.model.viewmodel.ContactListViewModel

class ContactListViewModelFactory(private val repository: ContactRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactListViewModel::class.java)) {
            return ContactListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
