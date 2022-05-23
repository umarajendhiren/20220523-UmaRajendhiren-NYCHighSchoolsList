package com.androidapps.nychighschoolslists.fakeresourcesfortest

import androidx.lifecycle.MutableLiveData
import com.androidapps.nychighschoolslists.model.SchoolListResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SchoolDaoFake {
    var fakeData: SchoolApiResponseWithScoreFake = SchoolApiResponseWithScoreFake()
    val fakeSchoolData = fakeData.getListOfSchool()
    var fakeListOfSchoolsResponseFromNetwork = MutableLiveData<SchoolListResponseItem>()
    suspend fun insertAll(schools: List<SchoolListResponseItem>) {
        println("data inserted!")
    }

    fun getSchoolsOrderByName(): Flow<List<SchoolListResponseItem>> {
        return flow { fakeSchoolData }
    }

    fun getSchoolsOrderByTotalSatScoreFlow(): Flow<List<SchoolListResponseItem>> {
        return flow { fakeSchoolData }
    }

    suspend fun cleanUpDatabase() {
        fakeSchoolData.clear()
        print("data deleted")
    }
}