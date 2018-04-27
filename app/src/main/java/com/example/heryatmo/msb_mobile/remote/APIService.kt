package com.example.heryatmo.msb_mobile.remote

import com.example.heryatmo.msb_mobile.model.Login
import com.example.heryatmo.msb_mobile.model.Post
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @POST("loginAPI")
    abstract fun loginAPI(@Body login : Login) : Call<Login>

    @GET("tampilPost")
    abstract fun tampilPost() : Observable<List<Post>>

    companion object Factory {
        fun create(): APIService{
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://bluebox2.com/msb/")
                    .build()
            return retrofit.create(APIService::class.java);
        }
    }
}