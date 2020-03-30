package com.ikaru.cararena.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ikaru.cararena.dao.CarDao
import com.ikaru.cararena.models.CarModel

@Database(entities = arrayOf(CarModel::class), version = 1)
abstract class CarRoomDatabase : RoomDatabase(){
    abstract fun carDao() : CarDao
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