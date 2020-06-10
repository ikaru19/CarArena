package com.ikaru.cararena.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.ikaru.cararena.R
import com.ikaru.cararena.models.CarModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_compare_detail.*
import kotlinx.android.synthetic.main.activity_compare_detail.view.*
import kotlinx.android.synthetic.main.layout_compare.*

class CompareDetailActivity : AppCompatActivity() {
    lateinit var mobilSatu : CarModel
    lateinit var mobilDua : CarModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_detail)
        val intent = intent
        mobilSatu = intent.getParcelableExtra("mobilSatu")
        mobilDua = intent.getParcelableExtra("mobilDua")
        initData()



    }


    override fun onBackPressed() {
        super.onBackPressed()

        Animatoo.animateSlideRight(this);

    }


    private fun initData(){
        Picasso
            .get()
            .load(mobilSatu.img1)
            .placeholder(R.drawable.car_placeholder)// load the image
            .into(iv_car_satu) // select the ImageView to load it into

        Picasso
            .get()
            .load(mobilDua.img1)
            .placeholder(R.drawable.car_placeholder)// load the image
            .into(iv_car_dua) // select the ImageView to load it into

        tv_nama_car_satu.text = mobilSatu.type
        tv_nama_car_dua.text = mobilDua.type
        tv_harga_car_satu.text = mobilSatu.harga_otr
        tv_harga_car_dua.text = mobilDua.harga_otr
        tv_kapasitas_mesin_carone.text = mobilSatu.kapasistasMesin
        tv_kapasitas_mesin_cartwo.text = mobilDua.kapasistasMesin
        tv_jml_silinder_carone.text = mobilSatu.jmlSilinder
        tv_jml_silinder_cartwo.text = mobilDua.jmlSilinder
        tv_jml_katup_carone.text = mobilSatu.jmlKatup
        tv_jml_katup_cartwo.text = mobilDua.jmlKatup
        tv_tenaga_carone.text = mobilSatu.maxTenaga
        tv_tenaga_cartwo.text = mobilDua.maxTenaga
        tv_bahan_bakar_carone.text = mobilSatu.jenisBahanBakar
        tv_bahan_bakar_cartwo.text = mobilDua.jenisBahanBakar
        tv_kapasitas_bakar_carone.text = mobilSatu.kapasitasBahanBakar
        tv_kapasitas_bakar_cartwo.text = mobilDua.kapasitasBahanBakar
        tv_suspensi_dpn_carone.text = mobilSatu.suspensiDepan
        tv_suspensi_dpn_cartwo.text = mobilDua.suspensiDepan
        tv_suspensi_blk_carone.text = mobilSatu.suspensiBelakang
        tv_suspensi_blk_cartwo.text = mobilDua.suspensiBelakang
        tv_rasio_carone.text = mobilSatu.banAspekRasio
        tv_rasio_cartwo.text = mobilDua.banAspekRasio
        tv_tipe_transmisi_carone.text = mobilSatu.tipeTransmisi
        tv_tipe_transmisi_cartwo.text = mobilDua.tipeTransmisi
        tv_gearbox_carone.text = mobilSatu.tipeGearBox
        tv_gearbox_cartwo.text = mobilDua.tipeGearBox
        tv_panjang_carone.text = mobilSatu.dimensiPanjang
        tv_panjang_cartwo.text = mobilDua.dimensiPanjang
        tv_sumbu_roda_carone.text = mobilSatu.dimensiSumbuRoda
        tv_sumbu_roda_cartwo.text =  mobilDua.dimensiSumbuRoda
        tv_ground_carone.text = mobilSatu.dimensiGroundClearance
        tv_ground_cartwo.text = mobilDua.dimensiGroundClearance
        tv_berat_carone.text = mobilSatu.dimensiBerat
        tv_berat_cartwo.text = mobilDua.dimensiBerat
        tv_kargo_carone.text = mobilSatu.dimensiKargo
        tv_kargo_cartwo.text = mobilDua.dimensiKargo
        tv_pintu_carone.text = mobilSatu.jmlPintu
        tv_pintu_cartwo.text = mobilDua.jmlPintu
        tv_kursi_carone.text = mobilSatu.jmlKuris
        tv_kursi_cartwo.text = mobilDua.jmlKuris

    }
}
