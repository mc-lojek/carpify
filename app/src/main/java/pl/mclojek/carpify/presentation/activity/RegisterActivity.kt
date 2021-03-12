package pl.mclojek.carpify.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.mclojek.carpify.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}