package com.buchi.fullentry.cars.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InspectItem(
    val media: List<String>?,
    val name: String?,
    val response: String
): Parcelable
/*
{
    "medias": [],
    "name": "door fittings rim",
    "response": "good"
}
 */