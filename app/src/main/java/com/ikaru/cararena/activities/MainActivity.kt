package com.ikaru.cararena.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.custom.sliderimage.logic.SliderImage
import com.ikaru.cararena.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val clickListener = View.OnClickListener { view ->

        when (view.getId()) {
            R.id.menu_mobil_baru -> new_car()
            R.id.menu_mobil_bandingkan -> compare_car()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menu_mobil_baru.setOnClickListener(clickListener)
        menu_mobil_bandingkan.setOnClickListener(clickListener)

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


    }

    fun new_car(){
        Toast.makeText(this, "New Car", Toast.LENGTH_LONG).show()
    }

    fun compare_car(){
        Toast.makeText(this, "Compare", Toast.LENGTH_LONG).show()
    }
}
