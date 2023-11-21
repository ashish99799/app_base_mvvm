package com.base.app.testing.model.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoApp {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGym(gymData: GymData)

    @Query(value = "SELECT * FROM Gym WHERE id=:id LIMIT 1")
    fun getGymDetails(id: Int): LiveData<GymData>

    @Query(value = "SELECT * FROM Gym")
    fun getAllGymList(): LiveData<List<GymData>>

    @WorkerThread
    @Update
    fun updateGym(gymData: GymData)

    // =================================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPopularGym(popularGymData: PopularGymData)

    @Query(value = "SELECT * FROM PopularGym WHERE id=:id LIMIT 1")
    fun getPopularGymDetails(id: Int): LiveData<PopularGymData>

    @Query(value = "SELECT * FROM PopularGym")
    fun getAllPopularGyms(): LiveData<List<PopularGymData>>

    @Query(value = "SELECT * FROM PopularGym WHERE gym_id =:gym_id")
    fun getAllPopularGymList(gym_id: Int): LiveData<List<PopularGymData>>

    @Update
    fun updatePopularGym(gymData: PopularGymData)

}