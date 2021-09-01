package com.glogachev.mycrypto.data

import com.glogachev.mycrypto.data.db.AppDatabase
import com.glogachev.mycrypto.domain.CryptoRepository
import com.glogachev.mycrypto.domain.mapper.toDB
import com.glogachev.mycrypto.domain.mapper.toDomain
import com.glogachev.mycrypto.domain.model.Crypto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class CryptoRepositoryImpl(
    private val database: AppDatabase
) : CryptoRepository {

    override fun obtainCryptoList(): Single<List<Crypto>> {
        return database
            .cryptoDao()
            .all()
            .map {
                it.toDomain()
            }
    }

    override fun updateCrypto(crypto: Crypto): Completable {
        return Completable.fromCallable {
            database.cryptoDao().insert(crypto.toDB())
        }
    }

    override fun addCrypto(crypto: Crypto): Completable {
        return Completable
            .fromCallable {
                database
                    .cryptoDao()
                    .insert(crypto.toDB())
            }
    }

    override fun deleteCrypto(id: Int): Completable {
        return Completable
            .fromCallable {
                database
                    .cryptoDao()
                    .delete(id)
            }
    }
}