package com.trivi12.pobretito.viewModels

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.trivi12.pobretito.IncidentActivity
import com.trivi12.pobretito.LoginActivity
import com.trivi12.pobretito.R
import com.trivi12.pobretito.SignInActivity

class HomeViewModel(private val context:Context):ViewModel() {



    fun saveSession(email:String,password:String){

        val prefs = context.getSharedPreferences(context.getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("password",password)
        prefs.apply()
    }

    fun logOut(){

        val prefs = context.getSharedPreferences(context.getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()

        FirebaseAuth.getInstance().signOut()

        goLogIn()
    }

    fun showAlert(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Cerrar Sesion")
        builder.setMessage("¿Desea cerrar sesion?")
        builder.setPositiveButton("Aceptar"){dialog,wich -> logOut()}
        builder.setNegativeButton("Cancelar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun goLogIn(){
        val logInIntent = Intent(context, LoginActivity::class.java)
        logInIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        context.startActivity(logInIntent)
    }

    fun goIncident(email:String){
        val incidentIntent = Intent(context,IncidentActivity::class.java).apply {
            putExtra("email",email)
        }
        context.startActivity(incidentIntent)
    }

}