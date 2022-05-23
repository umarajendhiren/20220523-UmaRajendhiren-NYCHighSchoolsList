package com.androidapps.nychighschoolslists.model
/*This data class will be used by Retrofit,for serialization and deserialization.*/
data class SatScoreApiResponseItem(
    var dbn: String?,
    var num_of_sat_test_takers: String?,
    var sat_critical_reading_avg_score: String?,
    var sat_math_avg_score: String?,
    var sat_writing_avg_score: String?,
    var school_name: String?
)