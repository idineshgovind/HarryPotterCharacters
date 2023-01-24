package com.example.harrypottercharacters.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.harrypottercharacters.network.HpProperty

class DetailViewModel (hpProperty: HpProperty,app:Application):AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<HpProperty>()

    val selectedProperty : LiveData<HpProperty>
    get() = _selectedProperty
    init {
        _selectedProperty.value = hpProperty
    }
}