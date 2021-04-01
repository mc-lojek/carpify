package pl.mclojek.carpify.presentation.fragment

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.model.MarkerOptions
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import pl.mclojek.carpify.R
import pl.mclojek.carpify.presentation.adapter.FishRecyclerAdapter
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.databinding.FragmentMyFishBinding
import pl.mclojek.carpify.presentation.viewmodel.FishMapViewModel
import pl.mclojek.carpify.presentation.viewmodel.MyFishViewModel
import timber.log.Timber

class MyFishFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: FragmentMyFishBinding
    private lateinit var adapter: FishRecyclerAdapter
    private val viewModel: MyFishViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("foobar", "my fish fragment sie odpalil")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyFishBinding.inflate(inflater, container, false)

        linearLayoutManager = LinearLayoutManager(this.activity)
        binding.recyclerView.layoutManager = linearLayoutManager

        adapter = FishRecyclerAdapter(arrayListOf(), ::onClick);
        binding.recyclerView.adapter = adapter

        loadData()

        return binding.root
    }

    private fun loadData() {
        viewModel.fishListObservable.observeForever {
            Timber.d("wywoluje sie observer ${viewModel.fishFilter.weightFrom} - ${viewModel.fishFilter.weightTo}")
            Timber.d("ile rekordow: ${it.size}")
            if(it.size > 0) {
                adapter.changeList(it)
            }
        }
        viewModel.load()
    }

    private fun onClick(fish: Fish) {
        Timber.d("to kliknales: ${fish.species}")
        val bundle = Bundle()
        bundle.putParcelable("fish", fish)
        findNavController().navigate(R.id.fish_details_fragment, bundle)
    }
}