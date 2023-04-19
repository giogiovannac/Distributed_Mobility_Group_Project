package com.example.distributedmobilitygroupproject.network

import com.squareup.moshi.Json

/**
 * Student ID: 991555778
 * Distributed Mobility Group Project
 * created by davidromero
 * on 2023-04-19
 **/
data class SpotifyID (
    val id: String,
    @Json(name = "id") val artistID: String
)