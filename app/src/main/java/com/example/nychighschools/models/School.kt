package com.example.nychighschools.models

data class School(
    val dbn: String = "",
    val schoolName: String,
    val boro: String = "",
    val overviewParagraph: String = "",
    val school10thSeats: String = "",
    val academicOpportunities1: String = "",
    val academicOpportunities2: String = "",
    val academicOpportunities3: String = "",
    val academicOpportunities4: String = "",
    val academicOpportunities5: String = "",
    val ellPrograms: String = "",
    val languageClasses: String = "",
    val advancedPlacementCourses: String = "",
    val diplomaEndorsements: String = "",
    val neighborhood: String = "",
    val sharedSpace: String = "",
    val campusName: String = "",
    val buildingCode: String = "",
    val location: String,
    val phoneNumber: String = "",
    val faxNumber: String = "",
    val schoolEmail: String = "",
    val website: String = "",
    val subway: String = "",
    val bus: String = "",
    val grades2018: String = "",
    val finalGrades: String = "",
    val totalStudents: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val additionalInfo1: String = "",
    val extracurricularActivities: String = "",
    val psalSportsBoys: String = "",
    val psalSportsGirls: String = "",
    val psalSportsCoed: String = "",
    val schoolSports: String = "",
    val graduationRate: String = "",
    val attendanceRate: String = "",
    val pctStudentsEnoughVariety: String = "",
    val collegeCareerRate: String = "",
    val pctStudentsSafe: String = "",
    val girls: String = "",
    val boys: String = "",
    val pbat: String = "",
    val international: String = "",
    val specialized: String = "",
    val transfer: String = "",
    val ptech: String = "",
    val earlyCollege: String = "",
    val geoEligibility: String = "",
    val schoolAccessibilityDescription: String = "",
    var isFavorite: Boolean = false,
)