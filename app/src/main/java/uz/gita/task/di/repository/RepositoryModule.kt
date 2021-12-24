package uz.gita.task.di.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.task.domain.repository.impls.ContentsRepositoryImpl
import uz.gita.task.domain.repository.interfaces.ContentRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun getContentRepository(impl: ContentsRepositoryImpl): ContentRepository


}