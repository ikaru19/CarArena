package com.ikaru.cararena.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel (
                      val id : Int,
                      val username : String,
                      val password : String,
                      val email : String ,
                      val roles : String,
                      val accessToken : String,
                      val message : String
                     ):Parcelable
