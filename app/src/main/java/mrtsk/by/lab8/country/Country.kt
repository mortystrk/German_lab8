package mrtsk.by.lab8.country

import android.os.Parcel
import android.os.Parcelable
import java.util.*


data class Country(val name: String, val flag: Int, val cities: Array<String>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.createStringArray()) {
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Country

        if (name != other.name) return false
        if (flag != other.flag) return false
        if (!Arrays.equals(cities, other.cities)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + flag
        result = 31 * result + Arrays.hashCode(cities)
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(flag)
        parcel.writeStringArray(cities)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }
}