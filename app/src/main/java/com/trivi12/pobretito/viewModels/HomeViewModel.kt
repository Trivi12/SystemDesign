package com.trivi12.pobretito.viewModels

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.trivi12.pobretito.*
import com.trivi12.pobretito.models.User

class HomeViewModel(private val context:Context):ViewModel() {

    fun getSession():String{
        val prefs = context.getSharedPreferences(
            context.getString(R.string.prefs_file),
            Context.MODE_PRIVATE)

        val email = prefs.getString("email", null)
        val password = prefs.getString("password", null)

        if (email == null && password == null) {
            goLogIn()
        }
        return email.toString()
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
        val incidentIntent = Intent(context,IncidentActivity::class.java)
        incidentIntent.putExtra("email",email)
        context.startActivity(incidentIntent)
    }

    fun goHistory(email:String){
        val historyIntent = Intent(context,HistoryActivity::class.java)
        historyIntent.putExtra("email", email)
        context.startActivity(historyIntent)
    }

}