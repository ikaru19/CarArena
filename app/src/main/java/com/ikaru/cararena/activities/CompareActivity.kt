package com.ikaru.cararena.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.ikaru.cararena.R
import com.ikaru.cararena.models.CarCompare
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
                tv_car_dua.text = cars.get(1).type
                btn_compare_go.visibility = View.VISIBLE
                btn_compare_add.visibility = View.GONE
            }

            tv_car_satu.text = cars.get(0).type
        }else{
            btn_compare_clear.visibility = View.GONE

        }


        val intent = intent
        if (intent.getParcelableArrayListExtra<CarModel>("test") == null){
            Log.e("ASW","kosong")
        }else{
            cars = intent.getParcelableArrayListExtra<CarModel>("test")
                Log.e("ASW",cars.get(0).type)
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

    fun saveToRecent(car1 : CarModel , car2 : CarModel){
        var carsRecent : ArrayList<CarCompare> = ArrayList()
        carsRecent = Paper.book().read("carsCompare",ArrayList())
        var cars = CarCompare(car1,car2)
        carsRecent.add(cars)
        Paper.book().write("carsCompare",carsRecent)
    }

    fun goCompare(){
        saveToRecent(cars[0],cars[1])
        intent = Intent(this,CompareDetailActivity::class.java)
        intent.putExtra("mobilSatu",cars[0])
        intent.putExtra("mobilDua",cars[1])
        startActivity(intent)
        Animatoo.animateSlideLeft(this);
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

    override fun onBackPressed() {
        intent = Intent(this, MainActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        finish()
        Log.e("ASW","Back From Compare")

        startActivity(intent)
        Animatoo.animateSlideRight(this);
    }


}
