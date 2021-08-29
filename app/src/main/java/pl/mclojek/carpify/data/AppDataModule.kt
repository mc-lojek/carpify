package pl.mclojek.carpify.data

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import pl.mclojek.carpify.data.repository.FishRepositoryImpl
import pl.mclojek.carpify.data.repository.LakeRepositoryImpl
import pl.mclojek.carpify.data.repository.RemoteFishRepositoryImpl
import pl.mclojek.carpify.data.repository.RemoteLakeRepositoryImpl
import pl.mclojek.carpify.data.retrofit.RestService
import pl.mclojek.carpify.data.retrofit.RetrofitFactory
import pl.mclojek.carpify.domain.repository.FishRepository
import pl.mclojek.carpify.domain.repository.LakeRepository
import pl.mclojek.carpify.presentation.viewmodel.LakesViewModel

val dataModule = Kodein.Module("DataModule") {
    bind<LakeRepository>() with singleton { RemoteLakeRepositoryImpl(instance()) }
    bind<FishRepository>() with singleton { RemoteFishRepositoryImpl(instance()) }
    bind<RestService>() with singleton { RetrofitFactory.getService() }
}