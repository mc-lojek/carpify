package pl.mclojek.carpify.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import pl.mclojek.carpify.R
import pl.mclojek.carpify.domain.dict.DictManager
import timber.log.Timber

class SplashActivity() : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                DictManager.initialize(applicationContext) { count, all ->
                    Timber.d("zaladowalem ${count} / ${all}")
                    if (count >= all) {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }
}