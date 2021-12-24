package uz.gita.task.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class GeneralModule {
    @[Provides Singleton]
    fun getList():ArrayList<String> = ArrayList()

}