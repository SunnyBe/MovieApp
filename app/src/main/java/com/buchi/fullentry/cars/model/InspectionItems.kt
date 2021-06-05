package com.buchi.fullentry.cars.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InspectionItems(
    val inspectionItems: List<InspectItem>? = null,
    val name: String?,
    val comment: String?
): Parcelable
