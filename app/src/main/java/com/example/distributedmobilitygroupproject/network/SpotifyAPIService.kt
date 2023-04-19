package com.example.distributedmobilitygroupproject.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


/**
 * Student ID: 991555778
 * Distributed Mobility Group Project
 * created by davidromero
 * on 2023-04-17
 **/


private const val BASE_URL = "https://spotify23.p.rapidapi.com/artists/?ids=2w9zwq3AktTeYYMuhMjju8"
private const val RAPIDAPI_KEY = "8388516373msh1227ac17fbff7f3p1069c8jsna28adae39e46"
private const val RAPIDAPI_HOST = "spotify23.p.rapidapi.com"

/*
Create Retrofit object and tell it to fetch data from webservice,
aswell as be able to read JSON response; which is needed in RESTful API's
*/
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SpotifyIDApiService { //Fetch artist spoitfy ID given string name
    @Headers(
        "X-RapidAPI-Key: 8388516373msh1227ac17fbff7f3p1069c8jsna28adae39e46",
        "X-RapidAPI-Host: spotify23.p.rapidapi.com"
    )
    @GET("/artists/id")
    fun getArtistID(): List<SpotifyID>
}

object SpotifyApi {
    val retrofitService: SpotifyIDApiService by lazy { retrofit.create(SpotifyIDApiService::class.java) }
}