package com.androidapps.nychighschoolslists.fakeresourcesfortest


import com.androidapps.nychighschoolslists.model.SatScoreApiResponseItem
import com.androidapps.nychighschoolslists.model.SchoolListResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/*For Unit test the MainViewModel*/

class RepositoryFake @Inject constructor(
    val retrofitService: RetrofitServiceFake,
    val schoolDao: SchoolDaoFake
) {

    fun getSchoolsFlow(): Flow<SchoolListResponseItem> {
        return flow { retrofitService.fakeSchoolData }
    }

    suspend fun getSchoolsSatScores(): List<SatScoreApiResponseItem> {
        return retrofitService.fakeScoreData
    }

    suspend fun updateLocalDatabase(schoolList: List<SchoolListResponseItem>) {
        schoolDao.insertAll(schoolList)
    }

    fun getSchoolsFromLocalDatabaseOrderByName(): Flow<List<SchoolListResponseItem>> {
        return flow { schoolDao.getSchoolsOrderByName() }
    }

    fun getSchoolsFromLocalDatabaseOrderByScore(): Flow<List<SchoolListResponseItem>> {
        return schoolDao.getSchoolsOrderByTotalSatScoreFlow()
    }

    suspend fun cleanUpDB() {
        schoolDao.cleanUpDatabase()
    }
}
