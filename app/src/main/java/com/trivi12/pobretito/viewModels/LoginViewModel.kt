package com.trivi12.pobretito.viewModels

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.Settings.Global.getString
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.trivi12.pobretito.HomeActivity
import com.trivi12.pobretito.R
import com.trivi12.pobretito.SignInActivity
import com.trivi12.pobretito.commons.DataValidator
import com.trivi12.pobretito.commons.validateText
import kotlinx.coroutines.launch
import kotlin.contracts.contract

class LoginViewModel(private val context:Context):ViewModel() {

    var dataValidationMutable = MutableLiveData<DataValidator?>()

    fun validateUserData(email: String, password: String) {

        viewModelScope.launch {
            var dataValidation = DataValidator()

            if (!email.validateText()) {
                dataValidation.emailError = context.getString(R.string.error_message)
            }
            if (!password.validateText()) {
                dataValidation.passwordError = context.getString(R.string.error_message)
            }

            if (dataValidation.isSuccessfully()) {
                logIn(email, password)
            } else {
                Toast.makeText(context, "Por favor, ingrese todos sus datos", Toast.LENGTH_LONG)
                    .show()
            }

            dataValidationMutable.value = dataValidation
        }
    }

    fun logIn(email: String, password: String) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Sesion iniciada correctamente", Toast.LENGTH_LONG)
                        .show()
                    goHome()
                } else {
                    showAlert()
                }
            }

        saveSession(email,password)
    }

    fun saveSession(email:String,password:String){
        val prefs = context.getSharedPreferences(context.getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("password",password)
        prefs.apply()
    }

    fun goHome() {
        val homeIntent = Intent(context, HomeActivity::class.java)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        context.startActivity(homeIntent)

    }

    fun showAlert() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Ha ocurrido un error")
        builder.setMessage("Puede que los datos ingresados no sean correctos, o que el usuario no exista")
        builder.setPositiveButton("error", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun goSignIn() {
        val signInIntent = Intent(context, SignInActivity::class.java)
        context.startActivity(signInIntent)
    }
}