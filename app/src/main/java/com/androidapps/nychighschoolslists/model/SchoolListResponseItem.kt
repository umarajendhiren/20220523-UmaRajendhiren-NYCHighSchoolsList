package com.androidapps.nychighschoolslists.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/*This data class will be used by Retrofit,for serialization and deserialization.
* ROOM will use this class for create table and column is local SQLite database */
@Parcelize
@Entity(tableName = "schools")
data class SchoolListResponseItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "school_dbn")


    var dbn: String?,
    var city: String?,
    var phone_number: String?,
    var primary_address_line_1: String?,
    var num_of_sat_test_takers: String?,
    var sat_critical_reading_avg_score: String?,
    var sat_math_avg_score: String?,
    var sat_writing_avg_score: String?,
    var school_name: String?,
    var school_email: String?,
    var latitude: String?,
    var longitude: String?,
    var requirement1_1: String?,
    var requirement2_1: String?,
    var requirement3_1: String?,
    var requirement4_1: String?,
    var requirement5_1: String?,
    var website: String?,
    var total_students: Int?,
    var location: String?,
    var total_sat_score: Int?,
    var zip: String?,
    var state_code: String?,
    @Ignore
    var academicopportunities1: String?,
    @Ignore
    var academicopportunities2: String?,
    @Ignore
    var admissionspriority11: String?,
    @Ignore
    var admissionspriority21: String?,
    @Ignore
    var admissionspriority31: String?,
    @Ignore
    var attendance_rate: String?,
    @Ignore
    var bbl: String?,
    @Ignore
    var bin: String?,
    @Ignore
    var boro: String?,
    @Ignore
    var borough: String?,
    @Ignore
    var building_code: String?,
    @Ignore
    var bus: String?,
    @Ignore
    var census_tract: String?,
    @Ignore
    var code1: String?,
    @Ignore
    var community_board: String?,
    @Ignore
    var council_district: String?,
    @Ignore
    var directions1: String?,
    @Ignore
    var ell_programs: String?,
    @Ignore
    var extracurricular_activities: String?,
    @Ignore
    var fax_number: String?,
    @Ignore
    var finalgrades: String?,
    @Ignore
    var grade9geapplicants1: String?,
    @Ignore
    var grade9geapplicantsperseat1: String?,
    @Ignore
    var grade9gefilledflag1: String?,
    @Ignore
    var grade9swdapplicants1: String?,
    @Ignore
    var grade9swdapplicantsperseat1: String?,
    @Ignore
    var grade9swdfilledflag1: String?,
    @Ignore
    var grades2018: String?,
    @Ignore
    var interest1: String?,
    @Ignore
    var method1: String?,
    @Ignore
    var neighborhood: String?,
    @Ignore
    var nta: String?,
    @Ignore
    var offer_rate1: String?,
    @Ignore
    var overview_paragraph: String?,
    @Ignore
    var pct_stu_enough_variety: String?,
    @Ignore
    var pct_stu_safe: String?,
    @Ignore
    var program1: String?,
    @Ignore
    var school_10th_seats: String?,
    @Ignore
    var school_accessibility_description: String?,
    @Ignore
    var school_sports: String?,
    @Ignore
    var seats101: String?,
    @Ignore
    var seats9ge1: String?,
    @Ignore
    var seats9swd1: String?,

    @Ignore
    var subway: String?


) : Parcelable {

    constructor(

    ) : this(
        dbn = null,
        city = null,
        phone_number = null,
        primary_address_line_1 = null,
        num_of_sat_test_takers = null,
        sat_critical_reading_avg_score = null,
        sat_math_avg_score = null,
        sat_writing_avg_score = null,
        school_name = null,
        school_email = null,
        latitude = null,
        longitude = null,
        requirement1_1 = null,
        requirement2_1 = null,
        requirement3_1 = null,
        requirement4_1 = null,
        requirement5_1 = null,
        website = null,
        total_students = null,
        location = null,
        total_sat_score = null,
        zip = null,
        state_code = null,
        academicopportunities1 = null,
        academicopportunities2 = null,
        admissionspriority11 = null,
        admissionspriority21 = null,
        admissionspriority31 = null,
        attendance_rate = null,
        bbl = null,
        bin = null,
        boro = null,
        borough = null,
        building_code = null,
        census_tract = null,
        community_board = null,
        bus = null,
        code1 = null,
        council_district = null,
        directions1 = null,
        ell_programs = null,
        extracurricular_activities = null,
        fax_number = null,
        finalgrades = null,
        grade9geapplicants1 = null,
        grade9geapplicantsperseat1 = null,
        grade9gefilledflag1 = null,
        grade9swdapplicants1 = null,
        grade9swdapplicantsperseat1 = null,
        grade9swdfilledflag1 = null,
        grades2018 = null,
        interest1 = null,
        method1 = null,
        neighborhood = null,
        nta = null,
        offer_rate1 = null,
        overview_paragraph = null,
        pct_stu_enough_variety = null,
        pct_stu_safe = null,
        program1 = null,
        school_10th_seats = null,
        school_accessibility_description = null,
        school_sports = null,
        seats101 = null,
        seats9ge1 = null,
        seats9swd1 = null,

        subway = null,

        )

}


