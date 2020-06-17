package com.ikaru.cararena.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ikaru.cararena.R
import com.ikaru.cararena.adapters.CarAdapter
import com.ikaru.cararena.models.BackgroundModels
import com.ikaru.cararena.models.CarModel
import com.ikaru.cararena.repository.CarRepository
import com.ikaru.cararena.utils.DataRepository
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var cars : ArrayList<CarModel> = ArrayList()
    val clickListener = View.OnClickListener { view ->

        when (view.getId()) {
            R.id.menu_mobil_baru -> new_car()
            R.id.menu_mobil_bandingkan -> compare_car()
        }
    }

    val carAdapter =  CarAdapter(R.layout.item_car,cars)
    lateinit var carRepository : CarRepository
    val postServices = DataRepository.create()
    val backgroundsString: MutableList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menu_mobil_baru.setOnClickListener(clickListener)
        menu_mobil_bandingkan.setOnClickListener(clickListener)



        carRepository = CarRepository(this)
        // Create slider image component


        // add images URLs
//        val images = listOf(
//            "https://i.ytimg.com/vi/zGs93H8sVUQ/maxresdefault.jpg",
//            "https://i.ytimg.com/vi/v3TMucxq56E/maxresdefault.jpg",
//            "https://s2.bukalapak.com/uploads/promo_partnerinfo_bloggy/2842/Bloggy_1_puncak.jpg")
        getBackground()
        // Add image URLs to sliderImage
        slider.setItems(backgroundsString)
        slider.addTimerToSlide(2000)

        carAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
//            Toast.makeText(this , "onItemClick : " + cars.get(position).type , Toast.LENGTH_SHORT ).show()
            intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("Car",cars.get(position))
            startActivity(intent)
            true
        }

        getData()
        rv_car_popular_main.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_car_popular_main.adapter =  carAdapter

    }

    fun new_car(){
        intent = Intent(applicationContext, BrandActivity::class.java)
        intent.putExtra("from","main")
        startActivity(intent)
        Animatoo.animateSlideUp(this);
    }

    fun compare_car(){
        intent = Intent(applicationContext, CompareActivity::class.java)
        startActivity(intent)
        Animatoo.animateSlideUp(this);
    }

    fun getData(){
        var car_repo = carRepository.GetDataFromDB().execute().get()
        carRepository.getPostFromInternet()

        if (car_repo!!.size > 0){
            Log.e("ASW","DAPAT DATA")
            cars = car_repo as ArrayList<CarModel>
            carAdapter.refill(cars)
        } else {
            Log.e("ASW","ERROR")
            var intent = Intent(this,EmptyDataActivity::class.java)
            startActivity(intent)
            finish()
        }


    }


    private fun getBackground(){
        postServices.getBackgrounds().enqueue(object  : Callback<List<BackgroundModels>> {
            override fun onFailure(call: Call<List<BackgroundModels>>, t: Throwable) {
                backgroundsString.add("https://ik.imagekit.io/hj8sm3kk7/marketing/daihatsu-terios-1539604946.jpg")
                backgroundsString.add("https://ik.imagekit.io/hj8sm3kk7/marketing/mgzsdigitaladshomepage1400x509pxweb-1585033177.jpg")
                slider.setItems(backgroundsString)
            }

            override fun onResponse(
                call: Call<List<BackgroundModels>>,
                response: Response<List<BackgroundModels>>
            ) {
                var backgroundModels : ArrayList<BackgroundModels> = ArrayList()
                backgroundModels = response.body() as ArrayList<BackgroundModels>
                var isi = backgroundModels.last()
                backgroundsString.add(isi.url_img1)
                backgroundsString.add(isi.url_img2)
                backgroundsString.add(isi.url_img3)
                Log.e("ASW",backgroundsString.get(0))
                slider.setItems(backgroundsString)
            }

        })
    }


}
