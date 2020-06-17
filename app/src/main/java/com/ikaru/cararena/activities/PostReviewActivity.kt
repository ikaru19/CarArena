package com.ikaru.cararena.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.ikaru.cararena.R
import com.ikaru.cararena.models.ReviewModel
import com.ikaru.cararena.utils.DataRepository
import kotlinx.android.synthetic.main.activity_post_review.*
import kotlinx.android.synthetic.main.activity_review.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostReviewActivity : AppCompatActivity() {

    val postServices = DataRepository.create()
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "sp_user"
    lateinit var sharedPreferences : SharedPreferences
    var idModel = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_review)
        sharedPreferences = this.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        idModel = intent.extras?.get("car_id") as Int;
        val uname = sharedPreferences.getString("name",null)
        var isLogin = sharedPreferences.getBoolean("isLogin",false)

        if (!isLogin){
            intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("car_id",idModel)
            finish()
            startActivity(intent)
        }


        btn_post_review.setOnClickListener {
            val review = et_post_review.text.toString()
            if (uname.isNullOrEmpty() || idModel == null || review.isNullOrEmpty()){
                Toast.makeText(this,"Form Mohon Diisi !",Toast.LENGTH_SHORT).show()
            }else{
                postReview(uname,review,idModel)
            }
        }
    }

    fun postReview(uname : String , review : String , generalId : Int){
        postServices.postReview(uname,review,generalId).enqueue(object : Callback<ReviewModel>{
            override fun onFailure(call: Call<ReviewModel>, t: Throwable) {
                Log.e("ASW",t.toString())
                Toast.makeText(this@PostReviewActivity,"Mohon cek koneksi anda",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ReviewModel>, response: Response<ReviewModel>) {
                Toast.makeText(this@PostReviewActivity,"Berhasil menambahkan review",Toast.LENGTH_SHORT).show()
                intent = Intent(this@PostReviewActivity, ReviewActivity::class.java)
                intent.putExtra("car_id",idModel)
                finish()
                startActivity(intent)
            }

        })
    }
}
