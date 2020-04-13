package com.ikaru.cararena.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ikaru.cararena.R
import com.ikaru.cararena.models.CarModel
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_compare.*


class CompareActivity : AppCompatActivity() {

    val clickListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.mobil_satu -> searchCar(0)
            R.id.mobil_dua -> searchCar(1)
            R.id.btn_compare_add -> searchCarEmpty()
            R.id.btn_compare_go -> goCompare()
            R.id.btn_compare_clear -> carClear()
        }
    }


    var cars : ArrayList<CarModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare)
        mobil_satu.setOnClickListener(clickListener)
        mobil_dua.setOnClickListener(clickListener)
        btn_compare_go.setOnClickListener(clickListener)
        btn_compare_add.setOnClickListener(clickListener)
        btn_compare_go.visibility = View.GONE
        btn_compare_clear.setOnClickListener(clickListener)
        getPaperDB()
        if(cars.isNotEmpty()){
            if (cars.size > 1){
                tv_car_dua.text = cars.get(1).car_name
                btn_compare_go.visibility = View.VISIBLE
                btn_compare_add.visibility = View.GONE
            }

            tv_car_satu.text = cars.get(0).car_name
        }else{
            btn_compare_clear.visibility = View.GONE

        }


        val intent = intent
        if (intent.getParcelableArrayListExtra<CarModel>("test") == null){
            Log.e("ASW","kosong")
        }else{
            cars = intent.getParcelableArrayListExtra<CarModel>("test")
                Log.e("ASW",cars.get(0).car_name)
        }

    }


    fun searchCar(params : Int){
        Log.e("ASW","searchCar()")
        when(params){
           0 ->{
               if (cars.isEmpty()){
                   searchCarEmpty()
               }else{
//                   cars.removeAt(0)
                   searchCarWithParam(0)
               }

            }
            1 -> {
                if (cars.size > 1){
//                    cars.removeAt(1)
                    searchCarWithParam(1)
                }else{
                    searchCarEmpty()
                }

            }

        }
    }

    private fun searchCarEmpty(){
        Log.e("ASW","empty Param")
        Toast.makeText(this,"searchCar",Toast.LENGTH_LONG).show()
        intent = Intent(applicationContext, BrandActivity::class.java)
        intent.putExtra("from","compare")
        startActivity(intent)
    }

    private fun searchCarWithParam(params: Int){
        Log.e("ASW","with Param")
        intent = Intent(applicationContext, BrandActivity::class.java)
        intent.putExtra("from","compare")
        intent.putExtra("change",params)
        startActivity(intent)
    }

    private fun getPaperDB(){
        Paper.init(this)
        cars = Paper.book().read("cars", ArrayList())
    }

    fun save(){
        Paper.book().write("cars",cars)
    }

    fun goCompare(){

    }

    fun carClear(){
        Paper.book().destroy()
        var new : ArrayList<CarModel> = ArrayList()
        Paper.book().write("cars",new)
        val refresh = Intent(this, CompareActivity::class.java)
        finish()
        overridePendingTransition(0, 0)
        startActivity(refresh)
        overridePendingTransition(0, 0)

    }


}
