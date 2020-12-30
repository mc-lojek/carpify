package pl.mclojek.carpify.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import pl.mclojek.carpify.adapters.FishRecyclerAdapter
import pl.mclojek.carpify.data.Fish
import pl.mclojek.carpify.databinding.FragmentMyFishBinding

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
        fishList.add(Fish(123, "KARPIK", 120, 123456, "Kuleczka", "Foobar", "54.202809, 17.359770", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))
        fishList.add(Fish(123, "KARPIK drugi", 120, 123456, "Kuleczka", "Foobar", "54.152273, 17.357024", 123, 321, "whatever"))

        adapter = FishRecyclerAdapter(fishList, requireContext());
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}