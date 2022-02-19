package pl.mclojek.carpify.presentation.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import pl.mclojek.carpify.R
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.databinding.FragmentFishMapBinding
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.presentation.viewmodel.FishMapViewModel
import timber.log.Timber

class FishMapFragment : BaseMapFragment(), OnMapReadyCallback, KodeinAware {

    private lateinit var binding: FragmentFishMapBinding
    private val viewModel: FishMapViewModel by instance()
    private lateinit var map: GoogleMap

    private val observer = Observer<List<Fish>> { fish ->
        Timber.d("Czy ty cos tu widzisz? ${fish}")
        if(fish != null) {
            map.clear()
            fish.forEach {
                map.addMarker(MarkerOptions().position(it.getLatLng()).title("${it.species} ${it.weight.toInt()}kg")).tag = it
            }

            map.setOnInfoWindowClickListener {
                val bundle = Bundle()
                bundle.putParcelable("fish", it.tag as Parcelable?)
                findNavController().navigate(R.id.fish_details_fragment, bundle)
            }
        }
    }

    private val statusObserver = Observer<Resource<String>> {
        when (it) {
            is Resource.Success -> {
                binding.progressBar.visibility = View.GONE
                Timber.d("Sukcesik")
            }
            is Resource.Error -> {
                binding.progressBar.visibility = View.GONE
                Snackbar.make(binding.root, it.message!!, Snackbar.LENGTH_LONG).show()
                Timber.d("Errorek")
            }
            is Resource.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                Timber.d("Ladowanko")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFishMapBinding.inflate(inflater, container, false)

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        setupOnClicks()

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.mapType = GoogleMap.MAP_TYPE_SATELLITE

        viewModel.lake.let {
            map.moveCamera(CameraUpdateFactory.newLatLngBounds(viewModel.lake!!.getLatLngBounds(), 8))
            map.setLatLngBoundsForCameraTarget(viewModel.lake!!.getLatLngBounds())
        }

        viewModel.fishListObservable.observe(viewLifecycleOwner, observer)
        viewModel.fishStatusObservable.observe(viewLifecycleOwner, statusObserver)
    }

    private fun setupOnClicks() {
        binding.buttonFilter.setOnClickListener {
            findNavController().navigate(R.id.filter_fish_fragment)
        }

        binding.buttonAddFish.setOnClickListener {
            findNavController().navigate(R.id.add_fish_fragment)
        }
    }
}