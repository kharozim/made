package com.ozimos.core.data.remote.network

import com.ozimos.core.BuildConfig
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClientt {

    fun getOkhttp(): OkHttpClient {

        val hostName = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostName, "sha256/oD/WAoRPvbez1Y2dfYfuo4yujAcYHXdv1Ivb2v2MOKk=")
            .build()

        val okHttpClientBuilder = OkHttpClient.Builder()
        if (!BuildConfig.IS_RELEASE) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor { chain ->
            val mOriRequest: Request = chain.request()
            val request: Request = mOriRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .method(mOriRequest.method, mOriRequest.body)
                .build()
            chain.proceed(request)
        }
        okHttpClientBuilder.certificatePinner(certificatePinner)
        return okHttpClientBuilder.build()
    }

    private const val token =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMjY1OWZjZmE3NDNlN2I5ZDExYTY4MDA1Njc1ZDI4NiIsInN1YiI6IjViN2QwY2MyMGUwYTI2M2JjZDAwOGVhZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7Jjpjp3o3D8CyIrpN7I9RpPCLFncwF5u-m94UUlxY8A"

}