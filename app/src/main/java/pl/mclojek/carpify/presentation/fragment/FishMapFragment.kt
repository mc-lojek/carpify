package pl.mclojek.carpify.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import pl.mclojek.carpify.R
import pl.mclojek.carpify.databinding.FragmentFishMapBinding
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.presentation.activity.SingleLakeActivity
import pl.mclojek.carpify.presentation.viewmodel.FishMapViewModel
import timber.log.Timber

class FishMapFragment : Fragment(), OnMapReadyCallback, KodeinAware {

    override val kodein: Kodein by closestKodein()
    private var mMap: MapView? = null
    private lateinit var binding: FragmentFishMapBinding
    private val viewModel: FishMapViewModel by instance()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMap?.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFishMapBinding.inflate(inflater, container, false)

        mMap = binding.mapView
        mMap?.onCreate(savedInstanceState)
        mMap?.getMapAsync(this)

        binding.buttonFilter.setOnClickListener {
            findNavController().navigate(R.id.filter_fish_fragment)
        }

        binding.buttonAddFish.setOnClickListener {
            findNavController().navigate(R.id.add_fish_fragment)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mMap?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMap?.onPause()
    }

    override fun onStart() {
        super.onStart()
        mMap?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMap?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMap?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMap?.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

        if(viewModel.lake != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(viewModel.lake!!.getLatLngBounds(), 8))
            googleMap.setLatLngBoundsForCameraTarget(viewModel.lake!!.getLatLngBounds())
        }

        viewModel.fishListObservable.observeForever {
            Timber.d("wywoluje sie observer ${viewModel.fishFilter.weightFrom} - ${viewModel.fishFilter.weightTo}")
            Timber.d("ile rekordow: ${it.size}")
            if(it.size > 0) {
                googleMap.clear()
                it.forEach {
                    googleMap.addMarker(MarkerOptions().position(it.getLatLng()).title("${it.species} ${it.weight.toInt()}kg")).tag = it
                }
            }
            googleMap.setOnInfoWindowClickListener {
                val bundle = Bundle()
                bundle.putParcelable("fish", it.tag as Parcelable?)
                findNavController().navigate(R.id.fish_details_fragment, bundle)
            }
        }
        viewModel.load()
    }
}