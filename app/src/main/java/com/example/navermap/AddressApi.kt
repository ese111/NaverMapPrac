package com.example.navermap

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AddressApi {
    @GET("map-geocode/v2/geocode")
    suspend fun getAddress(
        @Header("X-NCP-APIGW-API-KEY-ID") id: String = "iv0vra03bd",
        @Header("X-NCP-APIGW-API-KEY") key: String = "TSYKrzPsR07onFpCosa0iTLS68X3XI2bn66QjzDX",
        @Query("query") name: String = "분당구 불정로 6"
    ): Response<MapResponse>
}

object ApiClient {

    private const val BASE_URL = "https://naveropenapi.apigw.ntruss.com/"

    @OptIn(ExperimentalSerializationApi::class)
    fun getApiClient(): AddressApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(AddressApi::class.java)
    }

    fun getOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }
}