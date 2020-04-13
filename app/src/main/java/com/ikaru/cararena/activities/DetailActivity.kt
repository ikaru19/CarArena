package com.ikaru.cararena.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ikaru.cararena.R
import com.ikaru.cararena.models.BrandModel
import com.ikaru.cararena.models.CarModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {
    lateinit var carModel: CarModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        carModel = intent.extras?.get("Car") as CarModel
        val images = listOf(
            carModel.img1,
            carModel.img2,
           carModel.img3)

        sliderDetail.setItems(images as List<String>)
        sliderDetail.addTimerToSlide(2000)
        tv_harga_mobil_detail.text = carModel.harga_otr
        tv_mobil_detail.text = carModel.car_name
        tv_jenis_transmisi_detail.text = carModel.jenis_transmisi
        tv_mesin_detail.text = carModel.mesin
        tv_tempat_duduk_detail.text = carModel.tempat_duduk
        tv_tenaga_detail.text = carModel.tenaga

    }
}
