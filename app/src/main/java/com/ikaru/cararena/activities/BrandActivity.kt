package com.ikaru.cararena.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ikaru.cararena.R
import com.ikaru.cararena.adapters.BrandAdapter
import com.ikaru.cararena.adapters.CarAdapter
import com.ikaru.cararena.models.BrandModel
import com.ikaru.cararena.models.CarModel
import com.ikaru.cararena.repository.BrandRepository
import kotlinx.android.synthetic.main.activity_brand.*

class BrandActivity : AppCompatActivity() {
    var brands : ArrayList<BrandModel> = ArrayList()
    val brandAdapter =  BrandAdapter(R.layout.item_brand,brands)
    lateinit var brandRepository : BrandRepository
    public var selfintent: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand)
        selfintent=this


        var from = intent.getStringExtra("from")


        Log.e("ASW",from)
        brandRepository = BrandRepository(this)
        getData()
        brandAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            Toast.makeText(this , "onItemClick : " + brands.get(position).carBrand , Toast.LENGTH_SHORT ).show()
            var change = 404
            if (from =="compare"){
                change = intent.getIntExtra("change",404)
                Log.e("ASW",change.toString())
            }
            intent = Intent(this@BrandActivity, ListActivity::class.java)
            intent.putExtra("Brand",brands.get(position))
            intent.putExtra("from",from)
            intent.putExtra("change",change)

            startActivity(intent)
            Animatoo.animateSlideLeft(this);
            true
        }
        rv_brand_activity_brand.layoutManager = GridLayoutManager(this,2)
        rv_brand_activity_brand.adapter = brandAdapter



    }

    fun getData(){
        var brand_repo = brandRepository.GetBrandsFromDB().execute().get()
        brandRepository.getBrandsFromInternet()
//        Log.e("ASW",brand_repo.get(0).car_brand)

        if (brand_repo!!.isNotEmpty()){
            Log.e("ASW","DAPAT DATA")

            brands = brand_repo as ArrayList<BrandModel>
            Log.e("ASW",brand_repo.get(0).carBrand)

            brandAdapter.refill(brand_repo)
        } else {
            Log.e("ASW","ERROR")
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateSlideRight(this);
    }


}
