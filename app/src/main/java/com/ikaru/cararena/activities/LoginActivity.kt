package com.ikaru.cararena.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.ikaru.cararena.R
import com.ikaru.cararena.models.UserModel
import com.ikaru.cararena.utils.DataRepository
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val postServices = DataRepository.create()
    var idModel = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        idModel = intent.extras?.get("car_id") as Int;
        tv_register_login.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this);
        }

        btn_login.setOnClickListener {
            val uname = et_username_login.text.toString()
            val password = et_password_login.text.toString()

            if (uname.isNullOrEmpty() && password.isNullOrEmpty()){
                Toast.makeText(this,"Mohon Username dan Password harap diisi",Toast.LENGTH_SHORT).show()
            }else{

                doLogin(uname,password)
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateSlideRight(this);
    }

    fun doLogin(uname:String , password : String){
        postServices.login(uname,password).enqueue(object : Callback<UserModel>{
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Log.e("ASW","Login Activity : " + t.toString())
            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.code() == 203){
                    Toast.makeText(this@LoginActivity,"Username atau Password Salah !",Toast.LENGTH_SHORT).show()
                }else if(response.code() == 200){
                    Log.e("ASW",response.body().toString())
                    saveLoginInfo(response.body())
                    intent = Intent(this@LoginActivity, PostReviewActivity::class.java)
                    intent.putExtra("car_id",idModel)
                    finish()
                    startActivity(intent)
                }else{

                }
            }

        })
    }

    fun saveLoginInfo(user : UserModel? = null){
        val sharedPreferences : SharedPreferences = this@LoginActivity.getSharedPreferences("sp_user",0)
        val editor = sharedPreferences.edit()
        editor.putString("name", user?.username)
        editor.putString("token",user?.accessToken)
        editor.putBoolean("isLogin", true)
        editor.apply()
    }
}
