package com.buchi.fullentry.utilities

import com.buchi.fullentry.cars.data.cache.CarData
import com.buchi.fullentry.cars.model.Car
import com.buchi.fullentry.movie.data.cache.MovieData
import com.buchi.fullentry.movie.model.Movie

object MockUtilities {
    fun testMovieList(vararg ids: Int): List<Movie> {
        return mutableListOf<Movie>().apply {
            ids.forEach { id ->
                add(testMovie(id))
            }
        }
    }

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

    fun testMovie(id: Int): Movie = Movie(
        id = id,
        title = "Test title $id",
        originalTitle = "Original test title $id",
        adult = false,
        originalLanguage = "EN",
        overview = "Test Overview $id",
        backdropPath = "test/backdroppath/$id/com",
        genreIds = listOf(0, 1),
        mediaType = "TestMedia:$id",
        popularity = id * Math.random(),
        posterPath = "test/posterPath/$id/com",
        voteAverage = "$id",
        voteCount = id * Math.random().toInt(),
        releaseDate = "2020-19-02"
    )

    val testMovieData = MovieData(
        id = 0,
        title = "Harray Porter",
        originalTitle = "Original test title",
        adult = false,
        originalLanguage = "EN",
        overview = "Test Overview",
        backdropPath = "test/backdroppath/0/com",
        genreIds = "1,2",
        mediaType = "TestMedia",
        popularity = Math.random(),
        posterPath = "test/posterPath/0/com",
        voteAverage = "0",
        voteCount = Math.random().toInt(),
        releaseDate = "2020-19-02"
    )

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