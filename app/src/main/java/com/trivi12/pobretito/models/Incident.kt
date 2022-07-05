package com.trivi12.pobretito.models

data class Incident(private val num:String,
                    private val description:String,
                    private val condition:String,
                    private val category:Category,
                    private val address:String) {
}