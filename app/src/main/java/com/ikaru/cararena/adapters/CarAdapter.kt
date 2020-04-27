package com.ikaru.cararena.adapters

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ikaru.cararena.R
import com.ikaru.cararena.models.CarModel
import com.squareup.picasso.Picasso

class CarAdapter(layoutResId: Int, data: List<CarModel>? = null ) :  BaseQuickAdapter<CarModel, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder, item: CarModel) {
        helper.setText(R.id.tv_title_car_1, item.type)
        helper.setText(R.id.tv_price_item_car , item.harga_otr)
        val myImageView: ImageView = helper.getView(R.id.iv_compare_car_1)
        Picasso
            .get()
            .load(item.img1) // load the image
            .into(myImageView) // select the ImageView to load it into
    }

    fun refill(items : List<CarModel>? = null){
        this.mData = items
        notifyDataSetChanged()
    }
}