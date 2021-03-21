package pl.mclojek.carpify.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import pl.mclojek.carpify.R
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.databinding.FragmentFishDetailsBinding
import timber.log.Timber

class FishDetailsFragment : Fragment() {


    private lateinit var binding: FragmentFishDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fish_details, container, false)

        val fish = requireArguments().getParcelable<Fish>("fish")



        if (fish != null) {
            Timber.d("znalazlem ${fish.species}")
            with(binding)
            {
                binding.fish = fish
                textViewDatetime.text = fish.datetime.toString()
                textViewBait.text = fish.bait
                textViewDesc.text = fish.desc

            }

        }
        else
        {
            Timber.d("Nie znalazlem")
        }

        return binding.root
    }
}