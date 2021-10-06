package pl.mclojek.carpify.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.databinding.FragmentLakesListBinding
import pl.mclojek.carpify.presentation.adapter.LakeRecyclerAdapter
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.presentation.activity.SingleLakeActivity
import pl.mclojek.carpify.presentation.utils.ItemClickedListener
import pl.mclojek.carpify.presentation.viewmodel.LakesViewModel
import timber.log.Timber


class LakesListFragment : Fragment(), KodeinAware, ItemClickedListener<Lake> {

    override val kodein: Kodein by closestKodein()
    private lateinit var binding: FragmentLakesListBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: LakeRecyclerAdapter
    private val viewModel: LakesViewModel by instance()

    private val observer = Observer<List<Lake>> { lakes ->

        binding.progressBar.visibility = View.GONE
        lakes.let { adapter.updateData(it) }
        /*
        when (it) {
            is Resource.Success -> {
                binding.progressBar.visibility = View.GONE
                adapter.updateData(it.data!!)
                Timber.tag("FOO").d("Sukcesik")
            }
            is Resource.Error -> {
                binding.progressBar.visibility = View.GONE
                Timber.tag("FOO").d("Errorek")
            }
            is Resource.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                Timber.tag("FOO").d("Ladowanko")
            }
        }
        */
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLakesListBinding.inflate(inflater, container, false)

        linearLayoutManager = LinearLayoutManager(this.activity)
        binding.recyclerView.layoutManager = linearLayoutManager
        adapter = LakeRecyclerAdapter(this)
        binding.recyclerView.adapter = adapter

        viewModel.lakesListObservable.observe(viewLifecycleOwner, observer)

        return binding.root
    }

    override fun onItemClicked(lake: Lake)
    {
        val intent = Intent(this.context, SingleLakeActivity::class.java)
        intent.putExtra("lake", lake)
        startActivity(intent)
    }
}