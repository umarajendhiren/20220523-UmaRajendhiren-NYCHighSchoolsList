package com.androidapps.nychighschoolslists.retrofit

import com.androidapps.nychighschoolslists.model.SatScoreApiResponseItem
import com.androidapps.nychighschoolslists.model.SchoolListResponseItem
import retrofit2.http.GET

interface RetrofitService {


    @GET("/resource/s3k6-pzi2.json")
    suspend fun getNYCSchools(): List<SchoolListResponseItem>

    @GET("/resource/f9bf-2cp4.json")
    suspend fun getScoreDetailForSchool(): List<SatScoreApiResponseItem>
}