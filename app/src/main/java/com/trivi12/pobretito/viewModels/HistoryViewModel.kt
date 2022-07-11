package com.trivi12.pobretito.viewModels

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.model.Document
import com.trivi12.pobretito.models.Incident
import com.trivi12.pobretito.viewModels.base.BaseViewModel
import kotlinx.coroutines.launch

class HistoryViewModel(context: Context,sharedPreferences: SharedPreferences):BaseViewModel(sharedPreferences) {

    val listIncidents = MutableLiveData<ArrayList<Incident?>?>()

    override fun removeObserver(lifecycleOwner: LifecycleOwner) {
        TODO("Not yet implemented")
    }

    fun getIncidents(email:String){

        val userIncidents = ArrayList<Incident>()

        val db = FirebaseFirestore.getInstance()

        viewModelScope.launch {

            db.collection("incidents").whereEqualTo("user",email)
                .get()
                .addOnSuccessListener { documents ->

                for (document in documents) {
                    val incident = Incident(document)
                    userIncidents.add(incident)
                }
                listIncidents.value = ArrayList(userIncidents)
            }
        }
    }
}