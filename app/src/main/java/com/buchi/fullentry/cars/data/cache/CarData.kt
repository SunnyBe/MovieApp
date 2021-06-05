package com.buchi.fullentry.cars.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarData(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
    @ColumnInfo(name = "year")
    val year: Long?,
    @ColumnInfo(name = "city")
    val city: String?,
    @ColumnInfo(name = "state")
    val state: String?,
    @ColumnInfo(name = "gradeScore")
    val gradeScore: Double?,
    @ColumnInfo(name = "sellingCondition")
    val sellingCondition: String?,
    @ColumnInfo(name = "hasWarranty")
    val hasWarranty: Boolean?,
    @ColumnInfo(name = "marketplacePrice")
    val marketplacePrice: Long,
    @ColumnInfo(name = "marketplaceOldPrice")
    val marketplaceOldPrice: Long,
    @ColumnInfo(name = "hasFinancing")
    val hasFinancing: Boolean,
    @ColumnInfo(name = "mileage")
    val mileage: Long,
    @ColumnInfo(name = "mileageUnit")
    val mileageUnit: String,
    @ColumnInfo(name = "installment")
    val installment: Int,
    @ColumnInfo(name = "depositReceived")
    val depositReceived: Boolean,
    @ColumnInfo(name = "loanValue")
    val loanValue: Int,
    @ColumnInfo(name = "websiteUrl")
    val websiteUrl: String,
    @ColumnInfo(name = "bodyTypeId")
    val bodyTypeId: String,
    @ColumnInfo(name = "sold")
    val sold: Boolean,
    @ColumnInfo(name = "hasThreeDImage")
    val hasThreeDImage: Boolean
)
