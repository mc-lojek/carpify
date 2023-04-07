package pl.mclojek.carpify.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.mclojek.carpify.data.repository.FishRepositoryImpl
import pl.mclojek.carpify.domain.repository.FishRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideFishRepository(): FishRepository = FishRepositoryImpl()

}