package com.ikaru.cararena.adapters

import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ikaru.cararena.R
import com.ikaru.cararena.models.CarModel
import com.ikaru.cararena.models.ReviewModel
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(layoutResId: Int, data: List<ReviewModel>? = null) :  BaseQuickAdapter<ReviewModel, BaseViewHolder>(layoutResId, data) {

    fun refill(items : List<ReviewModel>? = null){
        this.mData = items

        notifyDataSetChanged()
    }

    override fun convert(helper: BaseViewHolder, item: ReviewModel) {
        Log.e("ASW","test "+item.name)
        helper.setText(R.id.tv_username_review,item.name)
        helper.setText(R.id.tv_review_comment,item.review)
        helper.setText(R.id.tv_review_date,item.createAt)

    }
}