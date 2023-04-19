package com.example.distributedmobilitygroupproject.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.distributedmobilitygroupproject.network.SpotifyApi
import com.example.distributedmobilitygroupproject.network.SpotifyImage
//import com.example.distributedmobilitygroupproject.network.SpotifyID
import kotlinx.coroutines.launch

enum class SpotifyApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<SpotifyApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<SpotifyApiStatus> = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    //private val _ids = MutableLiveData<List<SpotifyID>>() //TODO
    private val _images = MutableLiveData<List<SpotifyImage>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val images: LiveData<List<SpotifyImage>> = _images


    init {
        getSpotifyImages()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getSpotifyImages() {

        viewModelScope.launch {
            _status.value = SpotifyApiStatus.LOADING
            try {
                _images.value = SpotifyApi.retrofitService.getSpotifyImages()
                _status.value = SpotifyApiStatus.DONE
            } catch (e: Exception) {
                _status.value = SpotifyApiStatus.ERROR
                _images.value = listOf()
            }
        }
    }
}