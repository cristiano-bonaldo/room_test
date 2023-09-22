package cvb.com.br.room_test.di

import cvb.com.br.room_test.data.datasource.PixabayDataSource
import cvb.com.br.room_test.data.remote.RemotePixabayDataSource
import cvb.com.br.room_test.network.ApiInterceptor
import cvb.com.br.room_test.network.RetrofitClient
import cvb.com.br.room_test.network.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiInterceptor: ApiInterceptor
    ) =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(RetrofitClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    //==============

    @PixabayDataSourceRemote
    @Singleton
    @Provides
    fun providesRemotePixabayDataSource(apiService: ApiService): PixabayDataSource = RemotePixabayDataSource(apiService)

}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PixabayDataSourceRemote




