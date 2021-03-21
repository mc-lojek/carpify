package pl.mclojek.carpify.presentation

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import pl.mclojek.carpify.presentation.viewmodel.FishMapViewModel
import pl.mclojek.carpify.presentation.viewmodel.LakesViewModel

val presentationModule = Kodein.Module("PresentationModule") {
    bind() from singleton { LakesViewModel(instance()) }
    bind() from singleton { FishMapViewModel(instance()) }
}