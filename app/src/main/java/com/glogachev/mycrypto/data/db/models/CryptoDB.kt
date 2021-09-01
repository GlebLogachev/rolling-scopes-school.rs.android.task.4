package com.glogachev.mycrypto.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "crypto_db")
data class CryptoDB(
    @PrimaryKey (autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val price: Float,
    val quantity: Float
)