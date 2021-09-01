package com.glogachev.mycrypto.domain.mapper

import com.glogachev.mycrypto.data.db.models.CryptoDB
import com.glogachev.mycrypto.domain.model.Crypto


internal fun Crypto.toDB(): CryptoDB {
    return CryptoDB(
        id = id,
        name = name,
        quantity = quantity,
        price = price
    )
}

internal fun List<CryptoDB>.toDomain(): List<Crypto> {
    return this.map {
        Crypto(
            id = it.id,
            name = it.name,
            price = it.price,
            quantity = it.quantity
        )
    }
}