package pl.mclojek.carpify.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import pl.mclojek.carpify.R
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.databinding.FragmentLakesMapBinding
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.presentation.activity.SingleLakeActivity
import pl.mclojek.carpify.presentation.utils.POLAND_BOUNDS
import pl.mclojek.carpify.presentation.utils.hideKeyboard
import pl.mclojek.carpify.presentation.viewmodel.LakesViewModel
import timber.log.Timber

class LakesMapFragment : BaseMapFragment(), KodeinAware, OnMapReadyCallback {

    private lateinit var binding: FragmentLakesMapBinding
    private val viewModel: LakesViewModel by instance()
    private lateinit var map: GoogleMap

    private val observer = Observer<List<Lake>> { lakes ->
        lakes.let {
            it.forEach {
                map.addMarker(MarkerOptions().position(it.getCenterLatLng()).title(it.name)).tag = it
            }
            map.setOnInfoWindowClickListener {
                val intent = Intent(this.context, SingleLakeActivity::class.java)
                intent.putExtra("lake", it.tag as Lake)
                startActivity(intent)
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
        binding = FragmentLakesMapBinding.inflate(inflater, container, false)

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        setupSearchView()
        hideKeyboard()

        return binding.root
    }

    private fun setupSearchView() {
        val searchEditText = binding.searchView.findViewById<EditText>(R.id.search_src_text)
        searchEditText.setTextColor(resources.getColor(R.color.white))
        val searchImage =
            binding.searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchImage.setImageResource(R.drawable.ic_baseline_search_24)
        searchEditText.clearFocus()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(POLAND_BOUNDS, 8))
        viewModel.lakesListObservable.observe(viewLifecycleOwner, observer)
        viewModel.lakesStatusObservable.observe(viewLifecycleOwner, statusObserver)
        hideKeyboard()
    }
}