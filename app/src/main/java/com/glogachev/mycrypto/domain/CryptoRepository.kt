package com.glogachev.mycrypto.domain

import com.glogachev.mycrypto.data.db.models.CryptoDB
import com.glogachev.mycrypto.domain.model.Crypto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface CryptoRepository {
    fun obtainCryptoList(): Single<List<Crypto>>
    fun addCrypto(crypto: Crypto) : Completable
    fun deleteCrypto(id: Int) : Completable
    fun updateCrypto(crypto: Crypto): Completable
}
