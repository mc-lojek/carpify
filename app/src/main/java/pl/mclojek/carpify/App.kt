package pl.mclojek.carpify

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import pl.mclojek.carpify.data.db.AppDatabase
import timber.log.Timber

class App: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))
        import(appModule)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(object : Timber.DebugTree() {
            override fun log(
                priority: Int, tag: String?, message: String, t: Throwable?
            ) {
                super.log(priority, "syf_$tag", message, t)
            }
        })
        AppDatabase.setContext(this)
    }
}