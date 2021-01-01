package pl.mclojek.carpify.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pl.mclojek.carpify.R
import pl.mclojek.carpify.adapters.FishRecyclerAdapter
import pl.mclojek.carpify.adapters.LakeRecyclerAdapter
import pl.mclojek.carpify.data.Fish
import pl.mclojek.carpify.data.Lake
import pl.mclojek.carpify.databinding.FragmentMyFishBinding
import timber.log.Timber

class LakesListFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: FragmentMyFishBinding
    private lateinit var adapter: LakeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
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

        val lakeList = ArrayList<Lake>()
        lakeList.add(Lake(321, "Jezioro Miłoszewskie", "Pomorskie", "52.014596,17.994455"))
        lakeList.add(Lake(321, "Krążno", "Pomorskie", "52.014596,17.994455"))
        lakeList.add(Lake(321, "Bobolice", "Pomorskie", "52.014596,17.994455"))
        lakeList.add(Lake(321, "Tuszynek", "Pomorskie", "52.014596,17.994455"))
        lakeList.add(Lake(321, "Miłoszewskie", "Pomorskie", "52.014596,17.994455"))
        lakeList.add(Lake(321, "Miłoszewskie", "Pomorskie", "52.014596,17.994455"))
        lakeList.add(Lake(321, "Miłoszewskie", "Pomorskie", "52.014596,17.994455"))
        lakeList.add(Lake(321, "Miłoszewskie", "Pomorskie", "52.014596,17.994455"))
        lakeList.add(Lake(321, "Miłoszewskie", "Pomorskie", "52.014596,17.994455"))
        lakeList.add(Lake(321, "Miłoszewskie", "Pomorskie", "52.014596,17.994455"))
        lakeList.add(Lake(321, "Miłoszewskie", "Pomorskie", "52.014596,17.994455"))
        lakeList.add(Lake(321, "Miłoszewskie", "Pomorskie", "52.014596,17.994455"))

        adapter = LakeRecyclerAdapter(lakeList, ::onClick);
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    private fun onClick(lake: Lake)
    {
        Timber.d("to kliknales: ${lake.name}")
        val bundle = Bundle()
        bundle.putParcelable("lake", lake)
        findNavController().navigate(R.id.fish_map_fragment, bundle)
    }
}