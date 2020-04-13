package com.ikaru.cararena.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ikaru.cararena.models.CarModel

@Dao
interface CarDao {
    @Insert
    fun insert(carModel: CarModel)

    @Query("DELETE FROM cars")
    fun deleteAll()

    @Query("SELECT * FROM cars")
    fun getAllPosts() : List<CarModel>

    @Query("SELECT * FROM cars ORDER BY createdAt DESC LIMIT 5 ")
    fun getNewCar() : List<CarModel>

    @Query("SELECT * FROM cars WHERE id_brand = :id_brand ORDER BY createdAt DESC")
    fun getNewCarByID(id_brand: Int) : List<CarModel>

    @Query("SELECT * FROM cars WHERE id_brand = :id_brand AND car_name LIKE '%' || :car_name || '%' ORDER BY createdAt DESC")
    fun getCarByName(car_name : String,id_brand: Int) : List<CarModel>
}