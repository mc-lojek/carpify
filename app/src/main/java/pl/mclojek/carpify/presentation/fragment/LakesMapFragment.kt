package pl.mclojek.carpify.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import pl.mclojek.carpify.R
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.databinding.FragmentLakesMapBinding
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.presentation.activity.SingleLakeActivity
import pl.mclojek.carpify.presentation.viewmodel.LakesViewModel
import timber.log.Timber

class LakesMapFragment : Fragment(), KodeinAware, OnMapReadyCallback {

    private val POLAND_BOUNDS = LatLngBounds(LatLng(48.834318, 14.019607), LatLng(54.938474, 24.280836))

    override val kodein: Kodein by closestKodein()
    private lateinit var binding: FragmentLakesMapBinding
    private val viewModel: LakesViewModel by instance()
    private var mapView: MapView? = null
    private lateinit var map: GoogleMap

    private val observer = Observer<Resource<List<Lake>>> {
        when (it) {
            is Resource.Success -> {
                binding.progressBar.visibility = View.GONE
                Timber.tag("FOO").d("Sukcesik")

                it.data!!.forEach { map.addMarker(MarkerOptions().position(it.getCenterLatLng()).title(it.name)).tag = it }

                map.setOnInfoWindowClickListener {
                    val intent = Intent(this.context, SingleLakeActivity::class.java)
                    intent.putExtra("lake", it.tag as Lake)
                    startActivity(intent)
                }
            }
            is Resource.Error -> {
                binding.progressBar.visibility = View.GONE
                Snackbar.make(binding.root, it.message!!, Snackbar.LENGTH_LONG).show()
                Timber.tag("FOO").d("Errorek")
            }
            is Resource.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                Timber.tag("FOO").d("Ladowanko")
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLakesMapBinding.inflate(inflater, container, false)

        mapView = binding.mapView
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)

        val searchEditText = binding.searchView.findViewById<EditText>(R.id.search_src_text)
        searchEditText.setTextColor(resources.getColor(R.color.white))
        val searchImage = binding.searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchImage.setImageResource(R.drawable.ic_baseline_search_24)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(POLAND_BOUNDS, 8))
        viewModel.lakesListObservable.observe(viewLifecycleOwner, observer)
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }
}