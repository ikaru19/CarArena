package com.ikaru.cararena.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.ikaru.cararena.R
import com.ikaru.cararena.adapters.ReviewAdapter
import com.ikaru.cararena.models.BackgroundModels
import com.ikaru.cararena.models.CarModel
import com.ikaru.cararena.models.ReviewModel
import com.ikaru.cararena.models.ReviewResponseModel
import com.ikaru.cararena.utils.DataRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_review.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewActivity : AppCompatActivity() {

    val postServices = DataRepository.create()
    var idModel = 0;
    var reviewsList : List<ReviewModel> = ArrayList()
    val reviewAdapter = ReviewAdapter(R.layout.item_review,reviewsList)
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "sp_user"
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        idModel = intent.extras?.get("car_id") as Int;
        rv_review.layoutManager = LinearLayoutManager(this)
        getReview()
        sharedPreferences = this.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
//        Log.e("ASW", reviewsList[0].name)

//        reviewAdapter.refill(reviewsList)
        var carId = intent.extras?.get("car_id") as Int
        rv_review.adapter = reviewAdapter
        btn_komen_review.setOnClickListener {

           intent = Intent(this@ReviewActivity, PostReviewActivity::class.java)
           intent.putExtra("car_id",carId)
            finish()
           startActivity(intent)
           Animatoo.animateSlideLeft(this);


        }
    }

    private fun getReview(){
       postServices.getReviewByID(idModel).enqueue(object : Callback<List<ReviewModel>>{
           override fun onFailure(call: Call<List<ReviewModel>>, t: Throwable) {
               Log.e("ASW",t.toString())
               intent = Intent(this@ReviewActivity, NoInternetActivity::class.java)
                finish()
               startActivity(intent)

           }

           override fun onResponse(
               call: Call<List<ReviewModel>>,
               response: Response<List<ReviewModel>>
           ) {
               Log.e("ASW",response.body().toString())
               if (response.body() != null){
                   reviewsList = response.body() as List<ReviewModel>
                   reviewAdapter.refill(reviewsList)
                   Log.e("ASW", reviewsList[0].name)
               }else{

               }

           }

       })
    }
}
