package com.app.masterboatapp.data.remote

import com.app.masterboatapp.data.ApiResponseData
import com.app.masterboatapp.data.Post
import com.app.masterboatapp.utils.Constants
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.H6)
    suspend fun getProducts(@Body jsonObject: JsonObject): Response<ApiResponseData>

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}