package com.ikaru.cararena.adapters

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ikaru.cararena.R
import com.ikaru.cararena.models.BrandModel
import com.ikaru.cararena.models.CarModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_brand.view.*

class BrandAdapter(layoutResId: Int, data: List<BrandModel>?=null ) :  BaseQuickAdapter<BrandModel, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder, item: BrandModel) {
        helper.setText(R.id.tv_brand_item_brand,item.car_brand)
        val myImageView: ImageView = helper.getView(R.id.iv_logo_item_brand)
        Picasso
            .get()
            .load(item.logo_url) // load the image
            .into(myImageView) // select the ImageView to load it into
    }

    fun refill(items : List<BrandModel>? = null){
        this.mData = items
        notifyDataSetChanged()
    }
}