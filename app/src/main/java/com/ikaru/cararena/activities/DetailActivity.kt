package com.ikaru.cararena.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ikaru.cararena.R
import com.ikaru.cararena.models.BrandModel
import com.ikaru.cararena.models.CarModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {
    lateinit var carModel: CarModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        carModel = intent.extras?.get("Car") as CarModel
        writeToLayout()

    }

    fun writeToLayout(){
        val images = listOf(
            carModel.img1,
            carModel.img2,
            carModel.img3)

        sliderDetail.setItems(images as List<String>)
        sliderDetail.addTimerToSlide(2000)
        tv_harga_mobil_detail.text = carModel.harga_otr
        tv_mobil_detail.text = carModel.type
        tv_mesin_detail.text = carModel.kapasitasMesin
        tv_jml_silinder_detail.text = carModel.jmlSilinder
        tv_jml_katup_detail.text = carModel.jmlKatup
        tv_tenaga_detail.text = carModel.maxTenaga
        tv_bahan_bakar_detail.text = carModel.jenisBahanBakar
        tv_kapasitas_bahan_bakar_detail.text = carModel.kapasitasBahanBakar
        tv_suspensi_depan_detail.text = carModel.suspensiDepan
        tv_ban_aspek_detail.text = carModel.banAspekRasio
        tv_suspensi_belakang_detail.text = carModel.suspensiBelakang
        tv_tipe_transmisi_detail.text = carModel.tipeTransmisi
        tv_tipe_gear_box_detail.text = carModel.tipeGearBox
        tv_dimensi_panjang_detail2.text = carModel.dimensiPanjang
        tv_dimensi_sumbu_detail.text = carModel.dimensiSumbuRoda
        tv_dimensi_berat_detail.text = carModel.dimensiBerat
        tv_dimensi_kargo_detail.text = carModel.dimensiKargo
        tv_dimensi_ground_detail2.text = carModel.dimensiGroundClearance
        tv_jml_pintu_detail.text = carModel.jmlPintu
        tv_jml_kursi_detail.text = carModel.jmlKuris

    }
}
