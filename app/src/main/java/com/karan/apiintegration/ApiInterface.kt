package com.karan.apiintegration

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/api/users")
   suspend fun getData():Response<ResponseModel>
}