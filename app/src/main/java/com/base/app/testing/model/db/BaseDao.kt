package com.base.app.testing.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
abstract class BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: T)
    @Update
    abstract fun update(entity: T)
    @Delete
    abstract fun delete(entity: T)
}