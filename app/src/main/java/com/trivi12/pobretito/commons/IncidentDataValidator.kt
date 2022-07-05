package com.trivi12.pobretito.commons

class IncidentDataValidator {

    var categoryError:String?=null
    var descriptionError:String?=null
    var addressError:String?=null

    fun isSuccessfully():Boolean{

        return categoryError.isNullOrEmpty() && descriptionError.isNullOrEmpty() &&
                addressError.isNullOrEmpty()
    }

}