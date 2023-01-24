package com.example.harrypottercharacters.network


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HpProperty(
    val id: Int,
    val character: String,
    val nickname: String,
    val hogwartsStudent: Boolean,
    val hogwartsHouse: String,
    val interpretedBy: String,
    val child: List<String>,
    @Json(name = "image") val imgSrcUrl:String
):Parcelable