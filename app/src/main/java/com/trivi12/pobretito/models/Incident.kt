package com.trivi12.pobretito.models

import com.google.firebase.firestore.QueryDocumentSnapshot

data class Incident(val document: QueryDocumentSnapshot) {

    var num:String? = null
    var description:String? =  null
    var condition:String? = null
    var category: String? = null
    var address:String? = null
    var date:String? = null

    init {
        this.num = document.id
        this.address = document.getString("address")
        this.category = document.getString("category")
        this.description = document.getString("description")
        this.condition = document.getString("condition")
        this.date = document.getString("date")
    }
}