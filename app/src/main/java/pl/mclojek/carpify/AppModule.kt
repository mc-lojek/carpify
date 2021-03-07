package pl.mclojek.carpify

import androidx.room.Room
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import pl.mclojek.carpify.db.AppDatabase

val appModule = Kodein.Module("appModule") {
    bind<AppDatabase>() with singleton {
        Room.databaseBuilder(instance(), AppDatabase::class.java, "app_database")
                .build()
    }
}