package com.buchi.fullentry.cars.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarModel (
    val id: Int,
    val name: String?,
    val imageUrl: String?,
    val make: CarMake?
): Parcelable

/*
"model": {
"modelFeatures": [],
"id": 950,
"name": "RX 300",
"imageUrl": "",
"wheelType": "2WD",
"series": "RX Series",
"make": {
"id": 61,
"name": "Lexus",
"imageUrl": "https://storage.googleapis.com/img.autochek.africa/brands/lexus.svg"
},
 */
