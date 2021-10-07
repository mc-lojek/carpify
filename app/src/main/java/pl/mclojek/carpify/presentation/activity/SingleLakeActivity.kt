package pl.mclojek.carpify.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import pl.mclojek.carpify.R
import pl.mclojek.carpify.databinding.ActivityMainBinding
import pl.mclojek.carpify.databinding.ActivitySingleLakeBinding
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.presentation.viewmodel.FishMapViewModel
import pl.mclojek.carpify.presentation.viewmodel.LakesViewModel
import timber.log.Timber

class SingleLakeActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private lateinit var binding: ActivitySingleLakeBinding
    private lateinit var navController: NavController
    private val viewModel: FishMapViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleLakeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        //NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        viewModel.lake = intent.getParcelableExtra<Lake>("lake")
        Timber.d(viewModel.lake?.name)
        viewModel.load()
    }
}