package com.buchi.fullentry.cars.data.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarDto(
    val id: String,
    val title: String?,
    val imageUrl: String?,
    val year: Long?,
    val city: String?,
    val state: String?,
    val gradeScore: Double?,
    val sellingCondition: String?,
    val hasWarranty: Boolean?,
    val marketplacePrice: Long,
    val marketplaceOldPrice: Long,
    val hasFinancing: Boolean,
    val mileage: Long,
    val mileageUnit: String,
    val installment: Int,
    val depositReceived: Boolean,
    val loanValue: Int,
    val websiteUrl: String,
    val bodyTypeId: String,
    val sold: Boolean,
    val hasThreeDImage: Boolean
    // Todo Add stats
) : Parcelable

/**

{
"id": "pdUCkqhrA",
"title": "Mercedes-Benz G 63 AMG",
"imageUrl": "https://media.autochek.africa/file/oaw1hocg.jpg",
"year": 2019,
"city": "Surulere",
"state": "Lagos",
"gradeScore": 5,
"sellingCondition": "new",
"hasWarranty": false,
"marketplacePrice": 135515008,
"marketplaceOldPrice": 135015008,
"hasFinancing": false,
"mileage": 10018,
"mileageUnit": "km",
"installment": 0,
"depositReceived": false,
"loanValue": 0,
"websiteUrl": "https://autochek.africa/ng/car/g-63 amg-mercedes-benz-ref-pdUCkqhrA",
"stats": {
"webViewCount": 33,
"webViewerCount": 31,
"interestCount": 0,
"testDriveCount": 0,
"appViewCount": 15,
"appViewerCount": 15,
"processedLoanCount": 0
},
"bodyTypeId": "3",
"sold": false,
"hasThreeDImage": false
}
        */