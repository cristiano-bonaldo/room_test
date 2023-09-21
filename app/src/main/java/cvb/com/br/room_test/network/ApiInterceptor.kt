package cvb.com.br.room_test.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val newUrl =
            originalHttpUrl.newBuilder()
                .addQueryParameter("key", "39583503-28e0f98e3d5628cc0ee839a50")
                .build()

        val requestBuilder = original.newBuilder().url(newUrl)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}