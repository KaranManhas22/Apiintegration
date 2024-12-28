package com.karan.apiintegration

import com.karan.apiintegration.constant.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retrofit {
    companion object {
        private val retrofit by lazy {  // lazy means we only initialize this here once
            val logging = HttpLoggingInterceptor()     // for checking response in log cat(Console)
            logging.setLevel(HttpLoggingInterceptor.Level.BODY) // see the body of the response

            var client = OkHttpClient.Builder().addInterceptor(logging).build() //to convert browser response to simple format
                 // pass the client to retrofit instance

            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //addConvertorFactory is use to determine how the response should be interpreted and converted to kotlin object
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(ApiInterface::class.java)
        }
    }
}
