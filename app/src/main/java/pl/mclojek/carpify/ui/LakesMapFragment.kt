package pl.mclojek.carpify.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import pl.mclojek.carpify.R
import pl.mclojek.carpify.databinding.FragmentLakesMapBinding
import pl.mclojek.carpify.viewmodels.LakesListViewModel
import timber.log.Timber

class LakesMapFragment : Fragment(), OnMapReadyCallback {

    private var mMap: MapView? = null
    private lateinit var binding: FragmentLakesMapBinding
    private val viewModel: LakesListViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(LakesListViewModel::class.java)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        mMap?.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLakesMapBinding.inflate(inflater, container, false)

        mMap = binding.mapView
        mMap?.onCreate(savedInstanceState)
        mMap?.getMapAsync(this)

        val foo = viewModel.getLakeList().value
        Timber.d(foo?.get(0)?.name)

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
        googleMap.addMarker(MarkerOptions().position(LatLng(0.0, 0.0)).title("Marker"))
    }
}