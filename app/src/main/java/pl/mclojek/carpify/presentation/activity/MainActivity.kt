package pl.mclojek.carpify.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import pl.mclojek.carpify.R
import pl.mclojek.carpify.databinding.ActivityMainBinding
import pl.mclojek.carpify.domain.dict.DictManager
import timber.log.Timber

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.d("${DictManager.isInitialized} zaczynam ${DictManager.species.size}")
        DictManager.species.values.forEach { Timber.d(it.nameVerbose) }

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }
}