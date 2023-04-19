package com.example.distributedmobilitygroupproject.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

/**
 * Student ID: 991555778
 * Distributed Mobility Group Project
 * created by davidromero
 * on 2023-04-17
 **/


private const val BASE_URL = "https://spotify23.p.rapidapi.com/artists/?ids=2w9zwq3AktTeYYMuhMjju8"

/*
Create Retrofit object and tell it to fetch data from webservice,
aswell as be able to read JSON response; which is needed in RESTful API's
*/
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface SpotifyIDApiService { //Fetch artist spoitfy ID given string name
    @GET("artist")
    fun getArtistID()
}

object SpotifyApi {
    val retrofitService: SpotifyIDApiService by lazy { retrofit.create(SpotifyIDApiService::class.java) }
}