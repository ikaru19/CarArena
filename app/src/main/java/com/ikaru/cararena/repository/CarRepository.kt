package com.ikaru.cararena.repository

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.ikaru.cararena.databases.CarRoomDatabase
import com.ikaru.cararena.models.CarModel
import com.ikaru.cararena.services.ApiService
import com.ikaru.cararena.utils.DataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarRepository(context: Context) {
    var context : Context = context
    var carDatabase : CarRoomDatabase? = null
    var cars : List<CarModel>? = null
    val postServices = DataRepository.create()
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "firsttimer"
//    val sharedPreferences : SharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)

    init {
        carDatabase = CarRoomDatabase.getDatabase(context)!!
    }

    fun getPostFromInternet(){
        postServices.getCar().enqueue(object : Callback<List<CarModel>>{
            override fun onFailure(call: Call<List<CarModel>>, t: Throwable) {
                Log.e("ASW", "errornya ${t.message}")
                Toast.makeText(context,"No Internet trying get data offline", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<CarModel>>,
                response: Response<List<CarModel>>
            ) {

                GetDataFromDB().execute().get()

                if(cars!!.isNotEmpty()){
                    Log.e("ASW","DATA ALREADY HERE")
                }else{
                    val data = response.body()
                    data?.map {
                        InsertTask(it).execute()
                    }
                }
            }

        })
    }

    private inner  class InsertTask(var carModel: CarModel) : AsyncTask<Void,Void,Boolean>(){
        override fun doInBackground(vararg params: Void?): Boolean {
            carDatabase!!.carDao().insert(carModel)
            return true
        }

        override fun onPostExecute(result: Boolean?) {
            if (result!!){
                Log.e("ASW","ADDED TO DATABASE")
            }
        }
    }

    inner class GetDataFromDB : AsyncTask<Void, Void, List<CarModel>>(){
        override fun doInBackground(vararg params: Void?): List<CarModel> {
            cars = carDatabase!!.carDao().getNewCar()
            return carDatabase!!.carDao().getNewCar()
        }

        override fun onPostExecute(result: List<CarModel>?) {
            if(result!!.size > 0){
                cars = result
            }else{
                Log.e("ASW","ERROR")
            }
        }



    }



}