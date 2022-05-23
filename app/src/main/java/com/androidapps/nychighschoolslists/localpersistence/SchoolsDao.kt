package com.androidapps.nychighschoolslists.localpersistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidapps.nychighschoolslists.model.SchoolListResponseItem
import kotlinx.coroutines.flow.Flow

/*This Dao is for ROOM database.
* The DAO maps method calls to database queries,
  so that when the Repository calls a method such as getSchoolsOrderByName(),
  Room will execute query "SELECT * FROM schools ORDER BY school_name".*/

@Dao
interface SchoolsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(schools: List<SchoolListResponseItem>)

    @Query("SELECT * FROM schools ORDER BY school_name")
    fun getSchoolsOrderByName(): Flow<List<SchoolListResponseItem>>

    @Query("SELECT * from schools  ORDER BY total_sat_score DESC ")
    fun getSchoolsOrderByTotalSatScoreFlow(): Flow<List<SchoolListResponseItem>>

    @Query("DELETE FROM schools")
    suspend fun cleanUpDatabase()
}