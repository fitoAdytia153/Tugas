package com.ubaya.advweek6160420098.model

import com.google.gson.annotations.SerializedName

data class Student(
    var id:String?,
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var dob:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)

data class Car(
    var id: String?,
    @SerializedName("make")
    var type: String?,
    @SerializedName("photo_url")
    var carUrl: String?,
    var model: String?,
    var year: String?,
    var color: String?,
    var price: String?,
    var features: List<String>?,
    var specs: CarSpecification?,
)

data class CarSpecification(
    var engine: String?,
    var transmission: String?,
    @SerializedName("fuel_type")
    var fuelType: String?,
)

