package com.trivi12.pobretito.viewModels

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.trivi12.pobretito.HomeActivity
import com.trivi12.pobretito.R
import com.trivi12.pobretito.commons.DataValidator
import com.trivi12.pobretito.commons.validateText
import kotlinx.coroutines.launch

class SignInViewModel(private val context:Context):ViewModel() {

    var dataValidationMutable = MutableLiveData<DataValidator?>()

    fun validateUserData(dni:String,name:String,surname:String,email:String,password:String){

        viewModelScope.launch{
            var dataValidation = DataValidator()

            if(!dni.validateText()){
                dataValidation.dniError = context.getString(R.string.error_message)
            }
            if(!name.validateText()){
                dataValidation.nameError = context.getString(R.string.error_message)
            }
            if(!surname.validateText()){
                dataValidation.surnameError = context.getString(R.string.error_message)
            }
            if(!email.validateText()){
                dataValidation.emailError = context.getString(R.string.error_message)
            }
            if(!password.validateText()){
                dataValidation.passwordError = context.getString(R.string.error_message)
            }

            if(dataValidation.isSuccessfully()){
                createUser(dni,name,surname,email)
                signIn(email,password)
            }
            else{
                Toast.makeText(context,"Por favor, ingrese todos sus datos",Toast.LENGTH_LONG).show()
            }

            dataValidationMutable.value = dataValidation
        }
    }



    fun createUser(dni:String,name:String,surname: String, email:String){

        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(email).set(
            hashMapOf("name" to name,
                "surname" to surname,
                "dni" to dni)
        )
    }

    fun signIn(email:String, password: String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            if(it.isSuccessful){
                Toast.makeText(context,"Usuario creado con exito",Toast.LENGTH_LONG).show()
                saveSession(email,password)
                goHome()
            }else{
                showAlert()
            }
        }
    }

    fun saveSession(email:String,password:String){

        val prefs = context.getSharedPreferences(context.getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("password",password)
        prefs.apply()
    }

    fun showAlert(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Ha ocurrido un error")
        builder.setMessage("Usuario ya existente. Por favor inicie sesion")
        builder.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }

    fun goHome(){
        val homeIntent = Intent(context, HomeActivity::class.java)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        context.startActivity(homeIntent)
    }
}