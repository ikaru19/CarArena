package com.ikaru.cararena.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ikaru.cararena.dao.BrandDao
import com.ikaru.cararena.dao.CarDao
import com.ikaru.cararena.models.BrandModel
import com.ikaru.cararena.models.CarModel

@Database(entities = arrayOf(CarModel::class,BrandModel::class), version = 2)
abstract class CarRoomDatabase : RoomDatabase(){
    abstract fun carDao() : CarDao
    abstract fun brandDao() : BrandDao

    companion object{
        private var INSTANCE : CarRoomDatabase? = null

        fun getDatabase(context: Context): CarRoomDatabase?{
            if (INSTANCE == null){
                synchronized(CarRoomDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        CarRoomDatabase::class.java,"cars.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}