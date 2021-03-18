package pl.mclojek.carpify.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import pl.mclojek.carpify.R
import pl.mclojek.carpify.presentation.adapter.LakeRecyclerAdapter
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.databinding.FragmentMyFishBinding
import pl.mclojek.carpify.presentation.activity.SingleLakeActivity
import pl.mclojek.carpify.presentation.utils.ItemClickedListener
import pl.mclojek.carpify.presentation.viewmodel.LakesViewModel
import timber.log.Timber


class LakesListFragment : Fragment(), KodeinAware, ItemClickedListener<Lake> {

    override val kodein: Kodein by closestKodein()
    private lateinit var binding: FragmentMyFishBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: LakeRecyclerAdapter
    private val viewModel: LakesViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyFishBinding.inflate(inflater, container, false)

        linearLayoutManager = LinearLayoutManager(this.activity)
        binding.recyclerView.layoutManager = linearLayoutManager
        adapter = LakeRecyclerAdapter(this)
        binding.recyclerView.adapter = adapter

        viewModel.load()

        viewModel.lakeListObservable.observeForever {
            if(it.size > 0) {
                Timber.d("zaladowalem i podmienilem liste o dlugosci ${it.size}")
                adapter.updateData(it)
            }
        }

        return binding.root
    }

    override fun onItemClicked(lake: Lake)
    {
        Timber.d("to kliknales: ${lake.name}")
//        val bundle = Bundle()
//        bundle.putParcelable("lake", lake)
//        findNavController().navigate(R.id.fish_map_fragment, bundle)
        val intent = Intent(this.context, SingleLakeActivity::class.java)
        intent.putExtra("lake", lake)
        startActivity(intent)
    }
}