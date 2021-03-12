package pl.mclojek.carpify.domain

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import pl.mclojek.carpify.domain.usecase.GetLakesListUseCase
import pl.mclojek.carpify.presentation.viewmodel.LakesViewModel

val domainModule = Kodein.Module("DomainModule") {
    bind() from singleton { GetLakesListUseCase(instance()) }
}