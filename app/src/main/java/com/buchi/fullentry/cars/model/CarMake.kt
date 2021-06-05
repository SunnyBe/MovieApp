package com.buchi.fullentry.cars.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarMake(
    val id: Int,
    val name: String?,
    val imageUrl: String?
): Parcelable
