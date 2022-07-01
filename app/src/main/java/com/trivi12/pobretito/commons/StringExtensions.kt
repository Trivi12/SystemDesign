package com.trivi12.pobretito.commons

fun String?.validateText(): Boolean {
    return !this.isNullOrEmpty()
}