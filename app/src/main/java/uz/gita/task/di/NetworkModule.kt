package uz.gita.task.di

import android.content.Context
import androidx.paging.PagingConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.task.BuildConfig.BASE_URL
import uz.gita.task.data.local.LocalStorage
import uz.gita.task.data.remote.api.Api
import uz.gita.task.utils.addLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun getApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @[Provides Singleton]
    fun getConfig():PagingConfig = PagingConfig(10)

    @[Provides Singleton]
    fun getGson(): Gson = Gson()

    @[Provides Singleton]
    fun getRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @[Provides Singleton]
    fun getOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addLoggingInterceptor(context)
            .build()
}