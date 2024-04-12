package com.app.masterboatapp.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.masterboatapp.data.ApiResponseData
import com.app.masterboatapp.data.Post
import com.app.masterboatapp.data.Repository
import com.app.masterboatapp.utils.NetWorkResult
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository, application: Application): BaseViewModel(application) {
    private val _response: MutableLiveData<NetWorkResult<ApiResponseData>> = MutableLiveData()
    val response: LiveData<NetWorkResult<ApiResponseData>> = _response

    private val _responseposts: MutableLiveData<NetWorkResult<List<Post>>> = MutableLiveData()
    val responseposts: LiveData<NetWorkResult<List<Post>>> = _responseposts

    fun getProductsList(jsonObject: JsonObject) = viewModelScope.launch {
        repository.getProductList(context, jsonObject).collect { values ->
            _response.value = values
        }
    }

    fun getPostsList() = viewModelScope.launch {
        repository.getPostList(context).collect { values ->
            _responseposts.value = values
        }
    }
}