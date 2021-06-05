package com.buchi.fullentry.utilities

import com.buchi.fullentry.cars.data.cache.CarData
import com.buchi.fullentry.cars.model.Car

object MockUtilities {
    fun testCarList(vararg ids: Int): List<Car> {
        return mutableListOf<Car>().apply {
            ids.forEach { id->
                add(testCar(id))
            }
        }
    }

    private fun testCar(id: Int): Car {
        return Car(
            id = "pdUCkqhr$id",
            title = "Mercedes-Benz G 63 AMG",
            imageUrl = "https://media.autochek.africa/file/oaw1hocg.jpg",
            year = 2019,
            city = "Surulere",
            state = "Lagos",
            gradeScore = 5.0,
            sellingCondition = "new",
            hasWarranty = false,
            mileage = 10018,
            mileageUnit = "km",
            installment = 0,
            depositReceived = false,
            loanValue = 0,
            websiteUrl = "https://autochek.africa/ng/car/g-63 amg-mercedes-benz-ref-pdUCkqhrA",
            bodyTypeId = "3",
            sold = false,
            hasThreeDImage = false,
            marketplacePrice = 135515008,
            marketplaceOldPrice = 135015008,
            hasFinancing = false,
            insured = null,
            vin = null,
            licensePlate = null,
            engineNumber = null,
            price = null,
            createdBy = null,
            marketplaceVisible = null,
            marketplaceVisibleDate = null,
            isFeatured = null,
            reasonForSelling = null,
            ownerId = null,
            model = null,
            popular = null,
            country = null,
            address = null,
            ownerType = null
        )
    }

    val testCarData = CarData(
        id = "pdUCkqhrA",
        title = "Mercedes-Benz G 63 AMG",
        imageUrl = "https://media.autochek.africa/file/oaw1hocg.jpg",
        year = 2019,
        city = "Surulere",
        state = "Lagos",
        gradeScore = 5.0,
        sellingCondition = "new",
        hasWarranty = false,
        mileage = 10018,
        mileageUnit = "km",
        installment = 0,
        depositReceived = false,
        loanValue = 0,
        websiteUrl = "https://autochek.africa/ng/car/g-63 amg-mercedes-benz-ref-pdUCkqhrA",
        bodyTypeId = "3",
        sold = false,
        hasThreeDImage = false,
        marketplacePrice = 135515008,
        marketplaceOldPrice = 135015008,
        hasFinancing = false
    )

    val testCar = Car(
        id = "pdUCkqhrA",
        title = "Mercedes-Benz G 63 AMG",
        imageUrl = "https://media.autochek.africa/file/oaw1hocg.jpg",
        year = 2019,
        city = "Surulere",
        state = "Lagos",
        gradeScore = 5.0,
        sellingCondition = "new",
        hasWarranty = false,
        mileage = 10018,
        mileageUnit = "km",
        installment = 0,
        depositReceived = false,
        loanValue = 0,
        websiteUrl = "https://autochek.africa/ng/car/g-63 amg-mercedes-benz-ref-pdUCkqhrA",
        bodyTypeId = "3",
        sold = false,
        hasThreeDImage = false,
        marketplacePrice = 135515008,
        marketplaceOldPrice = 135015008,
        hasFinancing = false,
        insured = null,
        vin = null,
        licensePlate = null,
        engineNumber = null,
        price = null,
        createdBy = null,
        marketplaceVisible = null,
        marketplaceVisibleDate = null,
        isFeatured = null,
        reasonForSelling = null,
        ownerId = null,
        model = null,
        popular = null,
        country = null,
        address = null,
        ownerType = null
    )
}