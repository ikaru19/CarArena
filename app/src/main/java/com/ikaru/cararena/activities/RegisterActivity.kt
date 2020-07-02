package com.ikaru.cararena.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.ikaru.cararena.R
import com.ikaru.cararena.models.UserModel
import com.ikaru.cararena.utils.DataRepository
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    val postServices = DataRepository.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        tv_login_register.setOnClickListener {
            onBackPressed()
            Animatoo.animateSlideRight(this);
        }
        btn_register.setOnClickListener {
            val uname = et_username_register.editText?.text.toString()
            val password = et_password_register.editText?.text.toString()
            val email = et_email_register.editText?.text.toString()
            if (uname.isNullOrEmpty() || password.isNullOrEmpty() || email.isNullOrEmpty()){
                Toast.makeText(this,"Mohon Form harap diisi",Toast.LENGTH_SHORT).show()
            }else{
                doRegister(uname, email, password)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateSlideRight(this);
    }

    fun doRegister(uname:String , email:String , password : String){
        postServices.register(uname,email,password).enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Toast.makeText(this@RegisterActivity,"Tolong hubungkan ke internet",Toast.LENGTH_SHORT).show()
                Log.e("ASW",t.toString())
            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.code() == 400){
                    Toast.makeText(this@RegisterActivity,"Username/Email sudah digunakan !",Toast.LENGTH_SHORT).show()
                }else if(response.code() == 200){
                    Toast.makeText(this@RegisterActivity,"Register Berhasil silahkan , Silahkan Login",Toast.LENGTH_SHORT).show()
                    onBackPressed()
                }else{
                    Toast.makeText(this@RegisterActivity,"Terjadi Kesalahan kode 500",Toast.LENGTH_SHORT).show()
                }
            }


        })
    }
}
