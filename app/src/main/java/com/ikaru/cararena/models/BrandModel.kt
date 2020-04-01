package com.ikaru.cararena.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brands")
data class BrandModel (@PrimaryKey @ColumnInfo(name = "id")val id:Int,
                        @ColumnInfo(name = "car_brand") val car_brand: String? = null,
                       @ColumnInfo(name = "logo_url") val  logo_url : String? = null ,
                       @ColumnInfo(name = "createdAt") val createdAt : String? = null
                       ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(car_brand)
        parcel.writeString(logo_url)
        parcel.writeString(createdAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BrandModel> {
        override fun createFromParcel(parcel: Parcel): BrandModel {
            return BrandModel(parcel)
        }

        override fun newArray(size: Int): Array<BrandModel?> {
            return arrayOfNulls(size)
        }
    }
}