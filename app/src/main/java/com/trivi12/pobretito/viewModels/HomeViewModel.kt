package com.trivi12.pobretito.viewModels

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.trivi12.pobretito.LoginActivity
import com.trivi12.pobretito.R
import com.trivi12.pobretito.SignInActivity

class HomeViewModel(private val context:Context):ViewModel() {


    fun logOut(){
        FirebaseAuth.getInstance().signOut()
        goLogIn()
    }

    fun goLogIn(){
        val logInIntent = Intent(context, LoginActivity::class.java)
        context.startActivity(logInIntent)
    }

}