package ir.vbile.app.batman.di.interceptor

import ir.vbile.app.batman.core.util.CoreConstants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationHeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request().url.newBuilder()
        if (CoreConstants.API_KEY.isNotBlank()) {
            originalRequest.addQueryParameter("apiKey", CoreConstants.API_KEY)
        }
        originalRequest.addQueryParameter("type", CoreConstants.TYPE_MOVIE)
        val newUrl = originalRequest.build()
        val newRequest = chain.request().newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}