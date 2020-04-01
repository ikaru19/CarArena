package com.ikaru.cararena.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ikaru.cararena.models.BrandModel
import com.ikaru.cararena.models.CarModel

@Dao
interface BrandDao {
    @Insert
    fun insert(brandModel: BrandModel)

    @Query("SELECT * FROM brands")
    fun getAllBrands() : List<BrandModel>
}