package com.ikaru.cararena.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.ikaru.cararena.R
import com.ikaru.cararena.repository.BrandRepository
import com.ikaru.cararena.repository.CarRepository

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long=3000 // 3 sec
    lateinit var carRepository : CarRepository
    lateinit var brandRepository : BrandRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        carRepository = CarRepository(this)
        brandRepository = BrandRepository(this)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this, MainActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)

        getData()
    }

    fun getData() {
        Log.e("ASW","1")
        brandRepository.getBrandsFromInternet()
        Log.e("ASW","2")
        carRepository.getPostFromInternet()
    }
}
