package nz.co.test.transactions.data.di.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.math.BigDecimal

/**
 * Moshi Adapter class to serialize and deserialize [BigDecimal]
 */
class BigDecimalAdapter {
    @ToJson
    fun toJson(bigDecimal: BigDecimal) = bigDecimal.toString()

    @FromJson
    fun fromJson(value: String) = BigDecimal(value)
}