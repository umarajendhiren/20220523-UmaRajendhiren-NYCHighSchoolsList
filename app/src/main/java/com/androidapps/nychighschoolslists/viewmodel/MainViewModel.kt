package com.androidapps.nychighschoolslists.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.androidapps.nychighschoolslists.retrofit.Repository
import com.androidapps.nychighschoolslists.model.SatScoreApiResponseItem
import com.androidapps.nychighschoolslists.model.SchoolListResponseItem

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.N)
@HiltViewModel

/*The MainViewModel is responsible for giving data to the Views.
* It will communicate with the Repository and SchoolDao and get the data for View. ,*/
class MainViewModel @Inject constructor(
    repository: Repository,
    mContext: Context
) : ViewModel() {

    var orderByScoreSelected = MutableLiveData<Boolean>()
    private var repo = repository;
    var mutableLiveScore: MutableLiveData<List<SatScoreApiResponseItem>> = MutableLiveData()
    var liveSchoolsWithScore: MutableLiveData<List<SchoolListResponseItem>> = MutableLiveData()
    var schoolsWithScoreList = mutableListOf<SchoolListResponseItem>()
    var isDatabaseUpdated = MutableLiveData<Boolean>()
    var selectedSchool = MutableLiveData<SchoolListResponseItem>()

    init {
        orderByScoreSelected.value = true
        getSchoolDetailsWithScoreFromNetwork()
    }

    /*
 * This method responsible for make network request and get JSON response

* If we make a network request in the UI thread, that will block the UI thread until it receives the response.

* Blocking UI thread makes app unresponsive and makes app freeze, which creates a bad user experience.

* To avoid that, I used Kotlin's Coroutine to do asynchronous requests and responses.*

* Each coroutine will run concurrently in the background thread. In this way, we can make more responsive apps.

* Coroutines started using ViewModelScope will be stopped and will not make any network requests when the screen is not visible to the user.

* getSchoolsFlow(),getSchoolsSatScores() both are suspending functions will be executed asynchronously.

* Once it receives the response from API, each school's DBN in the list of schools will be checked with the school's DBN in the list of score details.

* If DBN matches, a detailed score will be added to the SchoolAPiResponseItem and a whole list of schools will be added to the local SQLite database for offline support.*/

    fun getSchoolDetailsWithScoreFromNetwork() {

        viewModelScope.launch {
            var liveSchool = repo.getSchoolsFlow()
            var liveScore = repo.getSchoolsSatScores()
            mutableLiveScore.postValue(liveScore)

            try {
                var scoreForSchool: SatScoreApiResponseItem? = null
                liveSchool.onEach { school ->


                    mutableLiveScore.value?.stream()?.filter { school.dbn?.equals(it.dbn) == true }
                        ?.forEach {


                            scoreForSchool = it
                        }
                }.map { school ->

                    //score api response having score as string .
                    //when we convert string to int score we will get NumberFormatException if it contains real string instead of numeric string

                    if (scoreForSchool?.num_of_sat_test_takers.equals("s")


                    ) {
                        school.num_of_sat_test_takers = "0";
                        school.sat_critical_reading_avg_score = "0"
                        school.sat_math_avg_score = "0"
                        school.sat_writing_avg_score = "0"
                        school.total_sat_score = 0
                    } else {
                        school.sat_critical_reading_avg_score =
                            scoreForSchool?.sat_critical_reading_avg_score
                        school.num_of_sat_test_takers = scoreForSchool?.num_of_sat_test_takers
                        school.sat_math_avg_score = scoreForSchool?.sat_math_avg_score
                        school.sat_writing_avg_score = scoreForSchool?.sat_writing_avg_score

                        school.total_sat_score =
                            scoreForSchool?.sat_writing_avg_score?.toInt()?.let {
                                scoreForSchool?.sat_critical_reading_avg_score?.toInt()?.plus(it)
                                    ?.let {
                                        scoreForSchool?.sat_math_avg_score?.toInt()?.plus(it)
                                    }

                            }



                        addToList(school)
                    }
                }


                    .collect {
                    }
            } catch (e: Exception) {


                withContext(Dispatchers.Main) {
                    // Toast.makeText(this, "Something Went Wrong!", Toast.LENGTH_SHORT)
                }
            } finally {

                repo.cleanUpDB()
                repo.updateLocalDatabase(schoolsWithScoreList)
                isDatabaseUpdated.postValue(true)

            }
        }

    }


    fun addToList(updatedSchoolWithScore: SchoolListResponseItem) {

        schoolsWithScoreList.add(updatedSchoolWithScore)

    }

    fun addAllToList(SchoolWithScoreList: List<SchoolListResponseItem>) {
        schoolsWithScoreList.clear()
        schoolsWithScoreList.addAll(SchoolWithScoreList)
    }

    fun getSchoolDetailsFromLocalDatabase() {
        viewModelScope.launch {
            try {

                if (orderByScoreSelected.value == true) {

                    repo.getSchoolsFromLocalDatabaseOrderByScore().collect {

                        addAllToList(it)

                        liveSchoolsWithScore.postValue(schoolsWithScoreList)
                    }
                } else {


                    repo.getSchoolsFromLocalDatabaseOrderByName().collect {

                        addAllToList(it)

                        liveSchoolsWithScore.postValue(schoolsWithScoreList)
                    }


                }

            } catch (e: Exception) {

            } finally {

            }
        }

    }


    fun isNetworkAvailable(): Boolean {
        return repo.isInternetAvailable()
    }

    fun onClickSchoolName(SchoolWithScore: SchoolListResponseItem) {
        selectedSchool.value = SchoolWithScore

    }

    fun sortBySchoolName() {
        orderByScoreSelected.value = false
        getSchoolDetailsFromLocalDatabase()
    }

    fun sortByScore() {
        orderByScoreSelected.value = true
        getSchoolDetailsFromLocalDatabase()
    }


}


