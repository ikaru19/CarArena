package com.ikaru.cararena.repository

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.ikaru.cararena.databases.CarRoomDatabase
import com.ikaru.cararena.models.BrandModel
import com.ikaru.cararena.models.CarModel
import com.ikaru.cararena.utils.DataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrandRepository(context: Context) {
    var context : Context = context
    var carDatabase : CarRoomDatabase? = null
    var brands : List<BrandModel>? = null
    val postServices = DataRepository.create()

    init {
        carDatabase = CarRoomDatabase.getDatabase(context)!!
    }

    fun getBrandsFromInternet(){
        postServices.getBrands().enqueue(object : Callback<List<BrandModel>> {

            override fun onFailure(call: Call<List<BrandModel>>, t: Throwable) {
                Log.e("ASW", "errornya brands ${t.message}")

            }

            override fun onResponse(
                call: Call<List<BrandModel>>,
                response: Response<List<BrandModel>>
            ) {
                GetBrandsFromDB().execute().get()

                if(brands!!.isNotEmpty()){
                    Log.e("ASW","DATA ALREADY HERE")
                }else{
                    val data = response.body()
                    Log.e("ASW",data.toString())
                    data?.map {
                        InsertBrands(it).execute()
                    }
                }
            }

        })
    }

    private inner  class InsertBrands(var brandModel: BrandModel) : AsyncTask<Void,Void,Boolean>(){
        override fun doInBackground(vararg params: Void?): Boolean {
            carDatabase!!.brandDao().insert(brandModel)
            return true
        }

        override fun onPostExecute(result: Boolean?) {
            if (result!!){
                Log.e("ASW","ADDED TO DATABASE")
            }
        }
    }

    inner class GetBrandsFromDB : AsyncTask<Void, Void, List<BrandModel>>(){
        override fun doInBackground(vararg params: Void?): List<BrandModel> {
            brands = carDatabase!!.brandDao().getAllBrands()

            return carDatabase!!.brandDao().getAllBrands()
        }

        override fun onPostExecute(result: List<BrandModel>?) {
            if(result!!.isNotEmpty()){
                brands = result
            }else{
                Log.e("ASW","ERROR")
            }
        }
    }


}