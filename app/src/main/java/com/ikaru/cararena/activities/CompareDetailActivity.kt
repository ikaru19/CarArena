package com.ikaru.cararena.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.ikaru.cararena.R
import com.ikaru.cararena.models.CarModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_compare_detail.*
import kotlinx.android.synthetic.main.activity_compare_detail.view.*

class CompareDetailActivity : AppCompatActivity() {
    lateinit var mobilSatu : CarModel
    lateinit var mobilDua : CarModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_detail)
        val intent = intent
        mobilSatu = intent.getParcelableExtra("mobilSatu")
        mobilDua = intent.getParcelableExtra("mobilDua")
        initData()



    }

    private fun initData(){
        Picasso
            .get()
            .load(mobilSatu.img1) // load the image
            .into(iv_car_satu) // select the ImageView to load it into

        Picasso
            .get()
            .load(mobilDua.img1) // load the image
            .into(iv_car_dua) // select the ImageView to load it into

        tv_nama_car_satu.text = mobilSatu.type
        tv_nama_car_dua.text = mobilDua.type
        tv_harga_car_satu.text = mobilSatu.harga_otr
        tv_harga_car_dua.text = mobilDua.harga_otr

    }
    override fun onBackPressed() {
        super.onBackPressed()

        Animatoo.animateSlideRight(this);

    }
}
