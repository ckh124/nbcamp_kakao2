package com.example.nbcamp_kakao.api


import com.example.nbcamp_kakao.model.ImageSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SimpleApi {// 레트로핏 3가지 구성요서중 하나인 Interface
    @GET("v2/search/image")
    fun searchImage(
    @Header("Authorization") apiKey: String = Constants.AUTH_HEADER,
    @Query("query") query: String,
    @Query("sort") sort : String,
    @Query("page") page : Int,
    @Query("size") size : Int
    ) : Call<ImageSearchResponse>
}
