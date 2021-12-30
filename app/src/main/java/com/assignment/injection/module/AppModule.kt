package com.assignment.injection.module

import android.content.Context
import assignment.app.BuildConfig
import com.assignment.utils.UIThread
import com.data.Constants
import com.data.JobExecutor
import com.data.cache.db.UserDatabase
import com.data.cache.user.UserCache
import com.data.cache.user.UserCacheImpl
import com.data.dataSource.user.UserDataSourceFactory
import com.data.dataSource.user.UserDataSourceFactoryInterface
import com.data.network.RestFactory
import com.data.network.interceptor.NetworkInterceptor
import com.data.network.interceptor.RedirectInterceptor
import com.data.network.remote.user.UserRemote
import com.data.network.remote.user.UserRemoteImpl
import com.data.network.service.UserService
import com.data.repository.UserDataRepository
import com.domain.executor.PostExecutionThread
import com.domain.executor.ThreadExecutor
import com.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    fun provideBaseUrl() = Constants.hostUrl

    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext app: Context) : UserDatabase {
        return UserDatabase.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideUserDao(db: UserDatabase) = db.getUserDao()

    @JvmStatic
    @Singleton
    @Provides
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @JvmStatic
    @Singleton
    @Provides
    fun providePostThreadExecutor(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Singleton
    @Provides
    fun provideOkHttp(networkInterceptor: NetworkInterceptor,
                      redirectInterceptor: RedirectInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(redirectInterceptor)
            .addInterceptor(networkInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit {
        return RestFactory.makeRetrofit(isDebug = BuildConfig.DEBUG, okHttpClient = okHttpClient)
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit) = retrofit.create(UserService::class.java)

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserRemote(userRemoteImpl: UserRemoteImpl): UserRemote {
        return userRemoteImpl
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserRepository(userDataRepository: UserDataRepository): UserRepository {
        return userDataRepository
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideUserCache(userCacheImpl: UserCacheImpl): UserCache {
        return userCacheImpl
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataSourceFactory(userDataSourceFactory: UserDataSourceFactory): UserDataSourceFactoryInterface {
        return userDataSourceFactory
    }
}