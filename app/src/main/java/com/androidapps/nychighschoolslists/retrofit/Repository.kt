package com.androidapps.nychighschoolslists.retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import com.androidapps.nychighschoolslists.model.SatScoreApiResponseItem
import com.androidapps.nychighschoolslists.model.SchoolListResponseItem
import com.androidapps.nychighschoolslists.localpersistence.SchoolsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(

    val retrofitService: RetrofitService,
    val schoolDao: SchoolsDao,
    val mContext: Context

) {

    suspend fun getSchools(): List<SchoolListResponseItem> = withContext(Dispatchers.Default) {
        retrofitService.getNYCSchools()


    }

    suspend fun getSchoolsSatScores(): List<SatScoreApiResponseItem> =

        retrofitService.getScoreDetailForSchool()


    fun getSchoolsFlow(): Flow<SchoolListResponseItem> {

        return flow {
            var schoolList = getSchools();
            schoolList.forEach {

                emit(it)

            }
        }

    }


    suspend fun updateLocalDatabase(schoolList: List<SchoolListResponseItem>) {

        schoolDao.insertAll(schoolList)


    }

    fun getSchoolsFromLocalDatabaseOrderByName(): Flow<List<SchoolListResponseItem>> {
        return schoolDao.getSchoolsOrderByName()
    }

    fun getSchoolsFromLocalDatabaseOrderByScore(): Flow<List<SchoolListResponseItem>> {

        return schoolDao.getSchoolsOrderByTotalSatScoreFlow()
    }

    suspend fun cleanUpDB() {
        schoolDao.cleanUpDatabase()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun isInternetAvailable(): Boolean {
        var connectivityManager: ConnectivityManager =
            mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var nInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        var isConnected = nInfo?.isAvailable == true && nInfo.isConnected

        println("isConnected $isConnected")
        return isConnected

    }
}

