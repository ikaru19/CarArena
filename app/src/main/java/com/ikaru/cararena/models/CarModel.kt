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
                    @ColumnInfo(name = "type") val type: String? = null,
                    @ColumnInfo(name = "harga_otr") val harga_otr: String? = null,
                    @ColumnInfo(name = "kapasistasMesin") val kapasistasMesin: String?  = "0",
                    @ColumnInfo(name = "jmlSilinder") val jmlSilinder: String? = null,
                    @ColumnInfo(name = "jmlKatup") val jmlKatup: String? = null,
                    @ColumnInfo(name = "maxTenaga") val maxTenaga: String? = null,
                    @ColumnInfo(name = "jenisBahanBakar") val jenisBahanBakar : String? = null,
                    @ColumnInfo(name = "kapasitasBahanBakar") val kapasitasBahanBakar : String? = null,
                    @ColumnInfo(name = "suspensiDepan") val suspensiDepan : String? = null,
                    @ColumnInfo(name = "banAspekRasio") val banAspekRasio : String? = null,
                    @ColumnInfo(name = "suspensiBelakang") val suspensiBelakang : String? = null,
                    @ColumnInfo(name = "tipeTransmisi") val tipeTransmisi : String? = null,
                    @ColumnInfo(name = "tipeGearBox") val tipeGearBox : String? = null,
                    @ColumnInfo(name = "dimensiPanjang") val dimensiPanjang : String? = null,
                    @ColumnInfo(name = "dimensiSumbuRoda") val dimensiSumbuRoda : String? = null,
                    @ColumnInfo(name = "dimensiGroundClearance") val dimensiGroundClearance : String? = null,
                    @ColumnInfo(name = "dimensiBerat") val dimensiBerat : String? = null,
                    @ColumnInfo(name = "dimensiKargo") val dimensiKargo: String? = null,
                    @ColumnInfo(name = "jmlPintu") val jmlPintu : String? = null,
                    @ColumnInfo(name = "jmlKuris") val jmlKuris: String? = null,
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
        parcel.writeString(type)
        parcel.writeString(harga_otr)
        parcel.writeString(kapasistasMesin)
        parcel.writeString(jmlSilinder)
        parcel.writeString(jmlKatup)
        parcel.writeString(maxTenaga)
        parcel.writeString(jenisBahanBakar)
        parcel.writeString(kapasitasBahanBakar)
        parcel.writeString(suspensiDepan)
        parcel.writeString(banAspekRasio)
        parcel.writeString(suspensiBelakang)
        parcel.writeString(tipeTransmisi)
        parcel.writeString(tipeGearBox)
        parcel.writeString(dimensiPanjang)
        parcel.writeString(dimensiSumbuRoda)
        parcel.writeString(dimensiGroundClearance)
        parcel.writeString(dimensiBerat)
        parcel.writeString(dimensiKargo)
        parcel.writeString(jmlPintu)
        parcel.writeString(jmlKuris)
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