package pl.mclojek.carpify.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import pl.mclojek.carpify.R
import pl.mclojek.carpify.data.Fish
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
                textViewSpecies.text = fish.species
                textViewWeight.text = fish.weight.toString()
                textViewSize.text = fish.size.toString()
                textViewDatetime.text = fish.datetime.toString()
                textViewBait.text = fish.bait
                textViewDesc.text = fish.desc

            }

        }
        else
        {
            Timber.d("Nie znalazlem")
        }

        binding.textViewDesc.text = "WHATEVER"

        return binding.root
    }
}