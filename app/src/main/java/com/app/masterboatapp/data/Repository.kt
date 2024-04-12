package com.app.masterboatapp.data

import android.content.Context
import com.app.masterboatapp.data.remote.RemoteDataSource
import com.app.masterboatapp.data.remote.toResultFlow
import com.app.masterboatapp.utils.NetWorkResult
import com.google.gson.JsonObject
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getProductList(context: Context, jsonObject: JsonObject): Flow<NetWorkResult<ApiResponseData>> {
        return toResultFlow(context){
            remoteDataSource.getProducts(jsonObject)
        }
    }

    suspend fun getPostList(context: Context): Flow<NetWorkResult<List<Post>>> {
        return toResultFlow(context){
            remoteDataSource.getPosts()
        }
    }

}