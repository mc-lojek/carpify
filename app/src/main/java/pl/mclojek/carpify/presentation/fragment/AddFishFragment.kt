package pl.mclojek.carpify.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import pl.mclojek.carpify.R
import pl.mclojek.carpify.databinding.FragmentAddFishBinding
import pl.mclojek.carpify.presentation.viewmodel.FishMapViewModel

class AddFishFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private lateinit var binding: FragmentAddFishBinding
    private val viewModel: FishMapViewModel by instance()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddFishBinding.inflate(inflater, container, false)

        setupViews()
        setupButtons()

        return binding.root
    }

    private fun setupViews() {

    }

    private fun setupButtons() {
        binding.selectCoordsButton.setOnClickListener {
            findNavController().navigate(R.id.select_coords_fragment)
        }
    }
}