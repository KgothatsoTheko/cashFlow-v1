package za.co.varsitycollege.st10092141.cashflow_v1

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// a singleton instance of retrofit (environment) adapted from Medium
//https://medium.com/@pritam.karmahapatra/retrofit-in-android-with-kotlin-9af9f66a54a8#:~:text=Jan%2024,%202024.%2033.%20Retrofit%20is%20a%20popular%20HTTP%20client
//Pritam Kar Mahapatra
//https://medium.com/@pritam.karmahapatra?source=post_page-----9af9f66a54a8--------------------------------

object RetrofitInstance {

    //private const val BASE_URL = "http://192.168.8.101:1992/" // for running on physical device (computer ip)

    private const val BASE_URL = "https://crud-a-pi.vercel.app/" // current live environment for node.js REST API

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // Optional: Add logging
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}