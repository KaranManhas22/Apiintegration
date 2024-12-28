package com.karan.apiintegration

import okhttp3.Response

class Repo {

    suspend fun userRepo():retrofit2.Response<ResponseModel> {
        return retrofit.api.getData()
    }

}