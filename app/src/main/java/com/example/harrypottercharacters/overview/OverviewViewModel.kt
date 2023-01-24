package com.example.harrypottercharacters.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harrypottercharacters.network.HpApi
import com.example.harrypottercharacters.network.HpProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


enum class HpApiStatus{
    LOADING,ERROR,DONE
}
class OverviewViewModel : ViewModel() {
    private val _status = MutableLiveData<HpApiStatus>()

    val status: MutableLiveData<HpApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<HpProperty>>()
    val properties : LiveData<List<HpProperty>>
    get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<HpProperty>()
    val navigateToSelectedProperty: LiveData<HpProperty>
    get() = _navigateToSelectedProperty

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getHarryPotterCharacters()
    }

    private fun getHarryPotterCharacters() {
        coroutineScope.launch {
            var getPropertiesDeferred = HpApi.retrofitService.getCharactersAsync()
            try {
                _status.value = HpApiStatus.LOADING
                val listResult = getPropertiesDeferred.await()
                _status.value = HpApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _status.value = HpApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(hpProperty:HpProperty){
        _navigateToSelectedProperty.value=hpProperty
    }

    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }
}