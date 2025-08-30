package cl.uchile.postgrado.mobile.contactlist.model

import androidx.lifecycle.ViewModel
import cl.uchile.postgrado.mobile.contactlist.room.Contact

class ContactViewModel: ViewModel() {
    lateinit var contact: Contact
}