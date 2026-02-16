package com.example.rickandmorty.framework.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Calendar

class AuthorizationInterceptor(
    private val publicKey: String,
    private val privateKey: String,
    private val calendar: Calendar
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url

        val timestamp = (calendar.timeInMillis / MILLIS_TO_SECONDS_DIVISOR).toString()

        val hash = "$timestamp$privateKey$publicKey".md5()
        val newUrl = requestUrl.newBuilder()
            .addQueryParameter(QUERY_PARAMETER_TIMESTAMP, timestamp)
            .addQueryParameter(QUERY_PARAMETER_API_KEY, publicKey)
            .addQueryParameter(QUERY_PARAMETER_HASH, hash)
            .build()

        return chain.proceed(
            request
                .newBuilder()
                .url(newUrl)
                .build()
        )
    }

    private fun String.md5(): String {
        val md = MessageDigest.getInstance(HASH_ALGORITHM_MD5)
        return BigInteger(BIG_INTEGER_POSITIVE_SIGN, md.digest(toByteArray()))
            .toString(HEX_RADIX)
            .padStart(MD5_HASH_LENGTH, HASH_PADDING_CHAR)
    }

    private companion object {
        const val QUERY_PARAMETER_TIMESTAMP = "timestamp"
        const val QUERY_PARAMETER_API_KEY = "apiKey"
        const val QUERY_PARAMETER_HASH = "hash"
        const val BIG_INTEGER_POSITIVE_SIGN = 1
        const val MILLIS_TO_SECONDS_DIVISOR = 1000L
        const val HASH_ALGORITHM_MD5 = "MD5"
        const val HEX_RADIX = 16
        const val MD5_HASH_LENGTH = 32
        const val HASH_PADDING_CHAR = '0'
    }
}