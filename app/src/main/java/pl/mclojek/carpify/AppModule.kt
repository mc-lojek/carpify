package pl.mclojek.carpify

import org.kodein.di.Kodein
import pl.mclojek.carpify.data.dataModule
import pl.mclojek.carpify.domain.domainModule
import pl.mclojek.carpify.presentation.presentationModule

val appModule = Kodein.Module("AppModule") {
    importAll(dataModule, domainModule, presentationModule)
}