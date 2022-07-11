package com.trivi12.pobretito.viewModels

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.trivi12.pobretito.HomeActivity
import com.trivi12.pobretito.R
import com.trivi12.pobretito.commons.DataValidator
import com.trivi12.pobretito.commons.IncidentDataValidator
import com.trivi12.pobretito.commons.validateText
import com.trivi12.pobretito.models.Category
import com.trivi12.pobretito.models.Condition
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class IncidentViewModel(private val context: Context):ViewModel() {

    var dataValidationMutable = MutableLiveData<IncidentDataValidator?>()

    fun validateIncidentData(category:String,description:String,address:String,email:String){

        viewModelScope.launch{

            var dataValidation = IncidentDataValidator()

            if(!category.validateText()){
                dataValidation.categoryError = context.getString(R.string.error_message)
            }
            if(!description.validateText()){
                dataValidation.descriptionError = context.getString(R.string.error_message)
            }
            if(!address.validateText()){
                dataValidation.addressError = context.getString(R.string.error_message)
            }

            if(dataValidation.isSuccessfully()){
                showConfirmAlert(category, description, address, email)
            }
            else{
                Toast.makeText(context,"Por favor, ingrese los datos correspondientes", Toast.LENGTH_LONG).show()
            }

            dataValidationMutable.value = dataValidation
        }
    }

    fun createIncident(category: String,description: String,address: String,email: String){

        val db = FirebaseFirestore.getInstance()

        val random = Random()
        val num = random.nextInt(99999 - 0).toString()
        val condition = Condition.ENVIADO.toString()
        val date = setupDate()

        db.collection("incidents").document(num).set(
            hashMapOf("category" to category,
                "description" to description,
                "address" to address,
                "condition" to condition,
                "user" to email,
                "date" to date)
        )

        showAlert(num,email)
    }

    fun showAlert(num:String,email:String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Reclamo generado con exito")
        builder.setMessage("El reclamo fue generado con el numero ${num}")
        builder.setPositiveButton("Aceptar"){dialog,wich -> goHome()}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showConfirmAlert(category: String,description: String,address: String,email: String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Generar reclamo")
        builder.setMessage("Usted generara un reclamo con los siguientes datos:" +
                "\n\nCategoria: ${category}\nDescripcion: ${description}\nDireccion:${address}")

        builder.setPositiveButton("Aceptar"){dialog,wich -> createIncident(category,description,address,email)}
        builder.setNegativeButton("Cancelar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun goHome(){
        val homeIntent = Intent(context,HomeActivity::class.java)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        context.startActivity(homeIntent)
    }

    fun setupDate():String{
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = simpleDateFormat.format(Calendar.getInstance().time)
        return date
    }

}