package pl.mclojek.carpify

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application() {


    override fun onCreate() {
        super.onCreate()
        initTimber()
    }
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "C:%s: L: %s, M: %s",
                        super.createStackElementTag(element),
                        element.lineNumber,
                        element.methodName
                    )
                }
            })
        } else {
            //TODO here we can configure it to use AppCenter or sth similar
        }
    }

}