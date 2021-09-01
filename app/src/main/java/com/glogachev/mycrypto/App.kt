package com.glogachev.mycrypto

import android.app.Application
import androidx.room.Room
import com.glogachev.mycrypto.data.CryptoRepositoryImpl
import com.glogachev.mycrypto.data.db.AppDatabase
import com.glogachev.mycrypto.domain.CryptoRepository

class App : Application() {

    companion object {

        lateinit var repository: CryptoRepository
    }

    override fun onCreate() {
        super.onCreate()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "cryptoDatabase"
        ).build()

        repository = CryptoRepositoryImpl(
            database = db
        )
    }
}