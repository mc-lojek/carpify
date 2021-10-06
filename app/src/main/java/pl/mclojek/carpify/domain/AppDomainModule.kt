package pl.mclojek.carpify.domain

import org.kodein.di.Kodein

val domainModule = Kodein.Module("DomainModule") {
    //bind() from singleton { GetLakesListUseCase(instance()) }
}