package com.ikaru.cararena.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarModel(@PrimaryKey @ColumnInfo(name = "id")val id:Int,
                    @ColumnInfo(name = "id_brand")val id_brand:Int,
                    @ColumnInfo(name = "car_brand") val car_brand: String? = null,
                    @ColumnInfo(name = "logo_url")  val logo_url:String? = null,
                    @ColumnInfo(name = "createdAt")  val createdAt: String? = null,
                    @ColumnInfo(name = "car_name") val car_name: String? = null,
                    @ColumnInfo(name = "harga_otr") val harga_otr: String? = null,
                    @ColumnInfo(name = "angsuran") val angsuran: String?  = null,
                    @ColumnInfo(name = "mesin") val mesin: String? = null,
                    @ColumnInfo(name = "tenaga") val tenaga: String? = null,
                    @ColumnInfo(name = "tempat_duduk") val tempat_duduk: String? = null,
                    @ColumnInfo(name = "jenis_transmisi") val jenis_transmisi: String? = null,
                    @ColumnInfo(name = "img1") val img1: String? = null,
                    @ColumnInfo(name = "img2") val img2: String? = null,
                    @ColumnInfo(name = "img3") val img3: String? = null) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(id_brand)
        parcel.writeString(car_brand)
        parcel.writeString(logo_url)
        parcel.writeString(createdAt)
        parcel.writeString(car_name)
        parcel.writeString(harga_otr)
        parcel.writeString(angsuran)
        parcel.writeString(mesin)
        parcel.writeString(tenaga)
        parcel.writeString(tempat_duduk)
        parcel.writeString(jenis_transmisi)
        parcel.writeString(img1)
        parcel.writeString(img2)
        parcel.writeString(img3)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CarModel> {
        override fun createFromParcel(parcel: Parcel): CarModel {
            return CarModel(parcel)
        }

        override fun newArray(size: Int): Array<CarModel?> {
            return arrayOfNulls(size)
        }
    }
}