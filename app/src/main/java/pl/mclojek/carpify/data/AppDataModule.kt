package pl.mclojek.carpify.data

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import pl.mclojek.carpify.data.repository.LakeRepositoryImpl
import pl.mclojek.carpify.domain.repository.LakeRepository
import pl.mclojek.carpify.presentation.viewmodel.LakesViewModel

val dataModule = Kodein.Module("DataModule") {
    bind<LakeRepository>() with singleton { LakeRepositoryImpl() }
}