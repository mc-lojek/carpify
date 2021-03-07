package pl.mclojek.carpify.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import pl.mclojek.carpify.MainActivity
import pl.mclojek.carpify.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
        }, 2000)
    }
}