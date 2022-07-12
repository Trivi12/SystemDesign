package com.trivi12.pobretito.models

import com.google.firebase.firestore.QueryDocumentSnapshot


data class User(val document: QueryDocumentSnapshot) {

    var dni : String? = null
    var name: String? = null
    var surname:String? = null
    var email: String? = null

    init {
        this.email = document.id
        this.dni = document.getString("dni")
        this.name = document.getString("name")
        this.surname = document.getString("surname")
    }
}