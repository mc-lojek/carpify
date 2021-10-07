package pl.mclojek.carpify.data

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import pl.mclojek.carpify.data.repository.FishRepositoryImpl
import pl.mclojek.carpify.data.repository.LakeRepositoryImpl
import pl.mclojek.carpify.data.repository.RemoteFishRepositoryImpl
import pl.mclojek.carpify.data.retrofit.RestService
import pl.mclojek.carpify.data.retrofit.RetrofitFactory
import pl.mclojek.carpify.domain.repository.FishRepository
import pl.mclojek.carpify.domain.repository.LakeRepository

val dataModule = Kodein.Module("DataModule") {
    bind<LakeRepository>() with singleton { LakeRepositoryImpl(instance()) }
    bind<FishRepository>() with singleton { FishRepositoryImpl(instance()) }
    bind<RestService>() with singleton { RetrofitFactory.getService() }
}