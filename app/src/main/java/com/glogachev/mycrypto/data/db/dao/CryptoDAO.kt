package com.glogachev.mycrypto.data.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.glogachev.mycrypto.data.db.models.CryptoDB
import io.reactivex.rxjava3.core.Single


@Dao
interface CryptoDAO {
    @Query("SELECT * FROM crypto_db")
    fun all(): Single<List<CryptoDB>>

    @Insert (onConflict = REPLACE)
    fun insert(value : CryptoDB)

    @Query("DELETE FROM `crypto_db` where `id` = :id ")
    fun delete(id: Int)

    @Update
    fun update(value: CryptoDB)

    @Query (" SELECT * FROM crypto_db WHERE id = :id")
    fun getById(id: Int): Single<CryptoDB>
}