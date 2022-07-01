package com.trivi12.pobretito.models

data class User (private val dni : String,
                 private val name: String,
                 private val surname:String,
                 private val email: String,
                 private val password: String) {
}