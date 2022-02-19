package pl.mclojek.carpify.presentation.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import pl.mclojek.carpify.R
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.databinding.FragmentFishDetailsBinding
import timber.log.Timber

class FishDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFishDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fish_details, container, false)

        val fish = requireArguments().getParcelable<Fish>("fish")



        if (fish != null) {
            with(binding)
            {
                binding.fish = fish
                textViewDatetime.text = fish.datetime.toString()
                textViewBait.text = fish.bait
                textViewDesc.text = fish.desc
                Picasso.get()
                    .load(Uri.parse(fish.img))
                    .placeholder(R.drawable.carp)
                    .error(R.drawable.ic_gun_pointer)
                    .into(imageView)
            }

        }
        return binding.root
    }
}