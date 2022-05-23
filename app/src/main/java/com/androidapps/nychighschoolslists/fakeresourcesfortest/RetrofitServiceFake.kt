package com.androidapps.nychighschoolslists.fakeresourcesfortest

import com.androidapps.nychighschoolslists.model.SatScoreApiResponseItem
import com.androidapps.nychighschoolslists.model.SchoolListResponseItem
import kotlinx.coroutines.delay

/*For Unit test the MainViewModel*/
class RetrofitServiceFake {
    var fakeData: SchoolApiResponseWithScoreFake = SchoolApiResponseWithScoreFake()
    val fakeSchoolData = fakeData.getListOfSchool()
    val fakeScoreData = fakeData.getListOfScores()
    suspend fun getNYCSchools(): List<SchoolListResponseItem> {
        delay(1000)
        return fakeSchoolData
    }


    suspend fun getScoreDetailForSchool(): List<SatScoreApiResponseItem> {
        delay(1000)
        return fakeScoreData
    }
}