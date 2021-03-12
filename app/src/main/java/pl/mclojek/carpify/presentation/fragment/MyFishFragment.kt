package pl.mclojek.carpify.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pl.mclojek.carpify.R
import pl.mclojek.carpify.adapters.FishRecyclerAdapter
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.databinding.FragmentMyFishBinding
import timber.log.Timber

class MyFishFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: FragmentMyFishBinding
    private lateinit var adapter: FishRecyclerAdapter

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

        val fishList = ArrayList<Fish>()
        fishList.add(
            Fish(
                123,
                "KARPIK",
                1.20f,
                120,
                123456,
                "Kuleczka",
                "Foobar",
                "54.202809, 17.359770",
                123,
                321,
                "whatever"
            )
        )
        fishList.add(
            Fish(
                123,
                "KARPIK",
                1.20f,
                120,
                123456,
                "Kuleczka",
                "Foobar",
                "54.202809, 17.359770",
                123,
                321,
                "whatever"
            )
        )
        fishList.add(
            Fish(
                123,
                "KARPIK",
                1.20f,
                120,
                123456,
                "Kuleczka",
                "Foobar",
                "54.202809, 17.359770",
                123,
                321,
                "whatever"
            )
        )
        fishList.add(
            Fish(
                123,
                "KARPIK",
                1.20f,
                120,
                123456,
                "Kuleczka",
                "Foobar",
                "54.202809, 17.359770",
                123,
                321,
                "whatever"
            )
        )
        fishList.add(
            Fish(
                123,
                "KARPIK",
                1.20f,
                120,
                123456,
                "Kuleczka",
                "Foobar",
                "54.202809, 17.359770",
                123,
                321,
                "whatever"
            )
        )
        fishList.add(
            Fish(
                123,
                "KARPIK",
                1.20f,
                120,
                123456,
                "Kuleczka",
                "Foobar",
                "54.202809, 17.359770",
                123,
                321,
                "whatever"
            )
        )
        fishList.add(
            Fish(
                123,
                "KARPIK",
                1.20f,
                120,
                123456,
                "Kuleczka",
                "Foobar",
                "54.202809, 17.359770",
                123,
                321,
                "whatever"
            )
        )
        fishList.add(
            Fish(
                123,
                "KARPIK",
                1.20f,
                120,
                123456,
                "Kuleczka",
                "Foobar",
                "54.202809, 17.359770",
                123,
                321,
                "whatever"
            )
        )

        adapter = FishRecyclerAdapter(fishList, ::onClick);
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    private fun onClick(fish: Fish)
    {
        Timber.d("to kliknales: ${fish.species}")
        val bundle = Bundle()
        bundle.putParcelable("fish", fish)
        findNavController().navigate(R.id.fish_details_fragment, bundle)
    }
}