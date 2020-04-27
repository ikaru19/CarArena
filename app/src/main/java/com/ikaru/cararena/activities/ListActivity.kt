package com.ikaru.cararena.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ikaru.cararena.R
import com.ikaru.cararena.adapters.CarAdapter
import com.ikaru.cararena.models.BrandModel
import com.ikaru.cararena.models.CarModel
import com.ikaru.cararena.repository.CarRepository
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_list.*


class ListActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var carRepository : CarRepository
    lateinit var brandModel: BrandModel
    var cars : ArrayList<CarModel> = ArrayList()
    var carsCompare : ArrayList<CarModel> = ArrayList()
    val carAdapter =  CarAdapter(R.layout.item_car_full,cars)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var from = intent.getStringExtra("from")

        carRepository = CarRepository(this)
        brandModel = intent.extras?.get("Brand") as BrandModel

        getPaperDB()
        getData()

        carAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            Toast.makeText(this , "onItemClick : " + cars.get(position).type , Toast.LENGTH_SHORT ).show()
            if (from == "compare"){
                var change = intent.getIntExtra("change",404)
                Log.e("ASW", change.toString())
                if (change == 0 || change == 1){
                    changeCars(change,cars.get(position))
                }else{
                    carsCompare.add(cars.get(position))
                }
                save()
                Log.e("ASW",from)
                intent = Intent(this, CompareActivity::class.java)

                finish()
                startActivity(intent)
                Animatoo.animateSlideLeft(this);

            }else{
                intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("Car",cars.get(position))
                startActivity(intent)
            }


            true
        }

        rv_car_activity_list.adapter = carAdapter

        rv_car_activity_list.layoutManager = LinearLayoutManager(this)
        searchView.setOnQueryTextListener(this)
    }

    private fun getData(){
        var car_repo = carRepository.GetCarById().execute(brandModel.id).get()
        if (car_repo != null) {
            Log.e("ASW",car_repo.get(0).type)
            cars = car_repo as ArrayList<CarModel>
            carAdapter.refill(cars)
        }
    }

    private fun getDataByName(name : String){
        var car_repo = carRepository.GetCarByName(name,brandModel.id).execute().get()
        if (car_repo != null) {

            cars = car_repo as ArrayList<CarModel>
            carAdapter.refill(cars)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        rv_car_activity_list.visibility = View.GONE
        getDataByName(newText as String)
        rv_car_activity_list.visibility = View.VISIBLE
        return false

    }

    fun getPaperDB(){
        Paper.init(this)

        carsCompare = Paper.book().read("cars",ArrayList())
    }

    fun save(){
        Paper.book().write("cars",carsCompare)
    }

    fun changeCars(param : Int,carModel: CarModel){
        carsCompare[param] = carModel
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateSlideRight(this);
    }
}
