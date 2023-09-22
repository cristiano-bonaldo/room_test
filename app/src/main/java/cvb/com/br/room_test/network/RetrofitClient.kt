package cvb.com.br.room_test.network

object RetrofitClient {

    const val BASE_URL = "https://pixabay.com/"

    /*
    private val retrofit : Retrofit by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val apiInterceptor = ApiInterceptor()

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiInterceptor)
            .build()

        return@lazy Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
    }

    val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)
    */
}