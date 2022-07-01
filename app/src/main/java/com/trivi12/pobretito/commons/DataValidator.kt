package com.trivi12.pobretito.commons

class DataValidator() {

    var dniError:String?=null
    var nameError:String?=null
    var surnameError:String?=null
    var emailError:String?=null
    var passwordError:String?=null

    fun isSuccessfully():Boolean{
        return dniError.isNullOrEmpty() && nameError.isNullOrEmpty() && surnameError.isNullOrEmpty() &&
        emailError.isNullOrEmpty() && passwordError.isNullOrEmpty()
    }


}