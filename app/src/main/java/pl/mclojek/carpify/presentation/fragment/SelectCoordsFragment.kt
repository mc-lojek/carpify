package pl.mclojek.carpify.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import pl.mclojek.carpify.databinding.FragmentSelectCoordsBinding
import pl.mclojek.carpify.presentation.viewmodel.FishMapViewModel

class SelectCoordsFragment : Fragment(), OnMapReadyCallback, KodeinAware {

    override val kodein: Kodein by closestKodein()
    private var mMap: MapView? = null
    private lateinit var binding: FragmentSelectCoordsBinding
    private val viewModel: FishMapViewModel by instance()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectCoordsBinding.inflate(inflater, container, false)

        mMap = binding.mapView
        mMap?.onCreate(savedInstanceState)
        mMap?.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

        if(viewModel.lake != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(viewModel.lake!!.getLatLngBounds(), 8))
            googleMap.setLatLngBoundsForCameraTarget(viewModel.lake!!.getLatLngBounds())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMap?.onSaveInstanceState(outState)
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
}