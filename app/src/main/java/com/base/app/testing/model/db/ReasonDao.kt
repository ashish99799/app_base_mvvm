package com.base.app.testing.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class ReasonDao : BaseDao<GymData>() {

    @Query("SELECT * FROM Gym WHERE id=:id")
    abstract fun getGyms(id: Int): LiveData<GymData>

    override fun insert(entity: GymData) {

    }

    override fun update(entity: GymData) {

    }
}