package com.glogachev.mycrypto.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.glogachev.mycrypto.data.db.dao.CryptoDAO
import com.glogachev.mycrypto.data.db.models.CryptoDB

@Database(entities = [CryptoDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDAO
}