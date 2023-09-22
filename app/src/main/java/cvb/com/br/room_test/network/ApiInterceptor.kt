package cvb.com.br.room_test.network

import cvb.com.br.room_test.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val newUrl =
            originalHttpUrl.newBuilder()
                .addQueryParameter("key", BuildConfig.Pixabay_KEY)
                .build()

        val requestBuilder = original.newBuilder().url(newUrl)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}