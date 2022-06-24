package com.base.app.testing.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Gym")
data class GymData(
    @PrimaryKey @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "date_time") var date_time: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "favorite") var favorite: Boolean,
    @ColumnInfo(name = "price") var price: Double,
    @ColumnInfo(name = "rating") var rating: Double
)

@Entity(tableName = "PopularGym")
data class PopularGymData(
    @PrimaryKey @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "gym_id") var gym_id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "favorite") var favorite: Boolean,
    @ColumnInfo(name = "price") var price: Double,
    @ColumnInfo(name = "rating") var rating: Double,
    @ColumnInfo(name = "location") var location: String,
    @ColumnInfo(name = "image") var image: String
)