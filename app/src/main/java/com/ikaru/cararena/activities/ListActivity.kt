package com.ikaru.cararena.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ikaru.cararena.R
import com.ikaru.cararena.adapters.CarAdapter
import com.ikaru.cararena.models.BrandModel
import com.ikaru.cararena.models.CarModel
import com.ikaru.cararena.repository.CarRepository
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    lateinit var carRepository : CarRepository
    lateinit var brandModel: BrandModel
    var cars : ArrayList<CarModel> = ArrayList()
    val carAdapter =  CarAdapter(R.layout.item_car_full,cars)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        carRepository = CarRepository(this)
        brandModel = intent.extras?.get("Brand") as BrandModel
        getData()
        carAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            Toast.makeText(this , "onItemClick : " + cars.get(position).car_name , Toast.LENGTH_SHORT ).show()

            true
        }
        rv_car_activity_list.adapter = carAdapter
        rv_car_activity_list.layoutManager = LinearLayoutManager(this)
    }

    private fun getData(){
        var car_repo = carRepository.GetCarById().execute(brandModel.id).get()
        if (car_repo != null) {
            Log.e("ASW",car_repo.get(1).car_name)
            cars = car_repo as ArrayList<CarModel>
            carAdapter.refill(cars)
        }
    }
}
