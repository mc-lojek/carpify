package pl.mclojek.carpify.presentation.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputLayout
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import pl.mclojek.carpify.R
import pl.mclojek.carpify.databinding.FragmentFilterFishBinding
import pl.mclojek.carpify.presentation.adapter.LakeRecyclerAdapter
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.databinding.FragmentMyFishBinding
import pl.mclojek.carpify.presentation.activity.SingleLakeActivity
import pl.mclojek.carpify.presentation.utils.ItemClickedListener
import pl.mclojek.carpify.presentation.viewmodel.FishMapViewModel
import pl.mclojek.carpify.presentation.viewmodel.LakesViewModel
import timber.log.Timber
import java.util.*


class FilterFishFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private lateinit var binding: FragmentFilterFishBinding
    private val viewModel: FishMapViewModel by instance()

    var chipsArray = emptyArray<Chip>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterFishBinding.inflate(inflater, container, false)

        chipsArray = arrayOf(
                binding.chip0,
                binding.chip1,
                binding.chip2,
                binding.chip3,
                binding.chip4,
                binding.chip5,
        )

        chipsArray.forEach {
            it.setOnCheckedChangeListener { chip, isChecked ->
                onChipChecked(chip, isChecked)
            }
        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        binding.dateFromInput.setEndIconOnClickListener {
            // Respond to end icon presses
            val dpd = context?.let { it1 ->
                DatePickerDialog(it1, { view, year, month, dayOfMonth ->
                    binding.dateFromEdittext.setText("${year}/${month}/${dayOfMonth}")
                    Log.d("foo", "cos tu dziala???    Data: ${year}/${month}/${dayOfMonth}")
                }, year, month, day)
            }
            if (dpd != null) {
                dpd.show()
            }
        }

        binding.dateToInput.setEndIconOnClickListener {
            // Respond to end icon presses
            val dpd = context?.let { it1 ->
                DatePickerDialog(it1, { view, year, month, dayOfMonth ->
                    binding.dateToEdittext.setText("${year}/${month}/${dayOfMonth}")
                    Log.d("foo", "cos tu dziala???    Data: ${year}/${month}/${dayOfMonth}")
                }, year, month, day)
            }
            if (dpd != null) {
                dpd.show()
            }
        }

        binding.applyButton.setOnClickListener {
            viewModel.fishFilter.apply {
                if(binding.weightFromEdittext.text.toString().isNotEmpty())
                    weightFrom = binding.weightFromEdittext.text.toString().toFloat()
                if(binding.weightToEdittext.text.toString().isNotEmpty())
                    weightTo = binding.weightToEdittext.text.toString().toFloat()

                if(chipsArray[0].isChecked) {
                    speciesList = chipsArray.map { it.text.toString() }
                }
                else {
                    speciesList = chipsArray.filter { it.isChecked }.map { it.text.toString() }
                }
            }
            viewModel.load()
            findNavController().navigateUp()
        }

        return binding.root
    }

    private fun onChipChecked(chip: CompoundButton, isChecked: Boolean) {
        if(chip.id == R.id.chip0)
        {
            if(isChecked)
            {
                chipsArray[1].isChecked = false
                chipsArray[2].isChecked = false
                chipsArray[3].isChecked = false
                chipsArray[4].isChecked = false
                chipsArray[5].isChecked = false
            }
        }
        else {
            if(isChecked)
                chipsArray[0].isChecked = false
        }
    }
}