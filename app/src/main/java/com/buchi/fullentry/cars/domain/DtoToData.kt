package com.buchi.fullentry.cars.domain

import com.buchi.fullentry.cars.data.cache.CarData
import com.buchi.fullentry.cars.data.network.CarDto

fun CarDto.toData() = CarData(
    id,
    title,
    imageUrl,
    year,
    city,
    state,
    gradeScore,
    sellingCondition,
    hasWarranty,
    marketplacePrice,
    marketplaceOldPrice,
    hasFinancing,
    mileage,
    mileageUnit,
    installment,
    depositReceived,
    loanValue,
    websiteUrl,
    bodyTypeId,
    sold,
    hasThreeDImage
)