package cl.uchile.postgrado.mobile.contactlist.model.viewmodel

import androidx.lifecycle.ViewModel
import cl.uchile.postgrado.mobile.contactlist.model.room.Contact

class ContactViewModel: ViewModel() {
    lateinit var contact: Contact
}