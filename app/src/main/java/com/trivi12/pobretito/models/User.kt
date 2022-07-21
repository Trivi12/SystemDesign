package com.trivi12.pobretito.models

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot


data class User(val document: DocumentSnapshot) {

    var dni : String? = null
    var name: String? = null
    var surname:String? = null

    init {
        this.dni = document.getString("dni")
        this.name = document.getString("name")
        this.surname = document.getString("surname")
    }
}