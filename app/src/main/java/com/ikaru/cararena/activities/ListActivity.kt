package com.ikaru.cararena.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ikaru.cararena.R
import com.ikaru.cararena.models.BrandModel
import com.ikaru.cararena.repository.CarRepository

class ListActivity : AppCompatActivity() {

    lateinit var carRepository : CarRepository
    lateinit var brandModel: BrandModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        carRepository = CarRepository(this)
        brandModel = intent.extras?.get("Brand") as BrandModel
        getData()
    }

    private fun getData(){
        var car_repo = carRepository.GetCarById().execute(brandModel.id).get()
        if (car_repo != null) {
            Log.e("ASW",car_repo.get(1).car_name)
        }
    }
}
