package com.glogachev.mycrypto.domain.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Crypto(
    var id: Int = 0,
    val name: String,
    val price: Float,
    val quantity: Float
) : Parcelable {
    @IgnoredOnParcel
    val investmentAmount: String = (price * quantity).toString()
}