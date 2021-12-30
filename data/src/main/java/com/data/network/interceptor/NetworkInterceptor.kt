package com.data.network.interceptor

import android.content.Context
import com.data.network.RestFactory
import com.domain.error.DomainError
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(@ApplicationContext val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        if (!RestFactory.isOnline(context)) {
            throw DomainError.NoInternetException("There is no Internet Connection")
        }
        return chain.proceed(requestBuilder.build())
    }
}