package com.ikaru.cararena.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.custom.sliderimage.logic.SliderImage
import com.ikaru.cararena.R
import com.ikaru.cararena.adapters.CarAdapter
import com.ikaru.cararena.models.CarModel
import com.ikaru.cararena.repository.CarRepository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var cars : ArrayList<CarModel>? = ArrayList()
    val clickListener = View.OnClickListener { view ->

        when (view.getId()) {
            R.id.menu_mobil_baru -> new_car()
            R.id.menu_mobil_bandingkan -> compare_car()
        }
    }

    val carAdapter =  CarAdapter(R.layout.item_car,cars)
    lateinit var carRepository : CarRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menu_mobil_baru.setOnClickListener(clickListener)
        menu_mobil_bandingkan.setOnClickListener(clickListener)



        carRepository = CarRepository(this)
        // Create slider image component
        val sliderImage = SliderImage(this)

        // add images URLs
        val images = listOf(
            "https://i.ytimg.com/vi/zGs93H8sVUQ/maxresdefault.jpg",
            "https://i.ytimg.com/vi/v3TMucxq56E/maxresdefault.jpg",
            "https://s2.bukalapak.com/uploads/promo_partnerinfo_bloggy/2842/Bloggy_1_puncak.jpg")

        // Add image URLs to sliderImage
        slider.setItems(images)
        slider.addTimerToSlide(2000)

        getData()
        rv_car_popular_main.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_car_popular_main.adapter =  carAdapter

    }

    fun new_car(){
        Toast.makeText(this, "New Car", Toast.LENGTH_LONG).show()
    }

    fun compare_car(){
        Toast.makeText(this, "Compare", Toast.LENGTH_LONG).show()
    }

    fun getData(){
//        val apiService = DataRepository.create()
//        apiService.getCar().enqueue(object : Callback<List<CarModel>>{
//            override fun onFailure(call: Call<List<CarModel>>, t: Throwable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onResponse(
//                call: Call<List<CarModel>>,
//                response: retrofit2.Response<List<CarModel>>
//            ) {
//                Log.e("ASW",response.body().toString())
//                cars = response.body() as ArrayList<CarModel>?
//                carAdapter.refill(cars)
//                Log.e("ASW", cars?.size.toString())
//            }
//
//        })
        var car_repo = carRepository.GetDataFromDB().execute().get()
        carRepository.getPostFromInternet()

        if (car_repo!!.size > 0){
            Log.e("ASW","DAPAT DATA")
            cars = car_repo as ArrayList<CarModel>?
            carAdapter.refill(cars)
        } else {
            Log.e("ASW","ERROR")

            restartApp()
        }


    }

    private fun restartApp() {
        val intent = Intent(this,MainActivity::class.java)

        startActivity(intent)

        finish()
    }


}
