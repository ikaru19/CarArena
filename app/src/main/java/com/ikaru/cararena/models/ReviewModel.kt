package com.ikaru.cararena.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewModel (val id:Int? = null,
                        val type:String? = null,
                        val name:String? = null,
                        val review:String? = null,
                        val createAt:String? = null
                        ):Parcelable