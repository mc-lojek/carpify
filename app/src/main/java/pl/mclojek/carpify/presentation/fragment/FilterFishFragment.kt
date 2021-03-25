package pl.mclojek.carpify.presentation.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import okhttp3.internal.format
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import pl.mclojek.carpify.R
import pl.mclojek.carpify.databinding.FragmentFilterFishBinding
import pl.mclojek.carpify.presentation.viewmodel.FishMapViewModel
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

        setupChips()
        setupViews()
        setupDialogs()
        setupButtons()






        return binding.root
    }

    private fun setupViews() {
        val filter = viewModel.fishFilter
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY)
        binding.apply {
            if(filter.timeFrom != 0L)
                dateFromEdittext.setText(sdf.format(Date(filter.timeFrom)))
            else
                dateFromEdittext.setText("")
            if(filter.timeTo != Long.MAX_VALUE)
                dateToEdittext.setText(sdf.format(Date(filter.timeTo)))
            else
                dateToEdittext.setText("")
            if(filter.weightFrom != 0.0f)
                weightFromEdittext.setText(String.format("%.2f", filter.weightFrom))
            else
                weightFromEdittext.setText("")
            if(filter.weightTo != Float.MAX_VALUE)
                weightToEdittext.setText(String.format("%.2f", filter.weightTo))
            else
                weightToEdittext.setText("")
            if(filter.lengthFrom != 0)
                lengthFromEdittext.setText(filter.lengthFrom.toString())
            else
                lengthFromEdittext.setText("")
            if(filter.lengthTo != Int.MAX_VALUE)
                lengthToEdittext.setText(filter.lengthTo.toString())
            else
                lengthToEdittext.setText("")

            Timber.d("species size: ${viewModel.fishFilter.speciesList.size}")
            if (viewModel.fishFilter.speciesList.size >= 5)
            {
                Timber.d("wchodiz w ifa")
                chip0.isChecked = true
            } else {
                Timber.d("Wchodiz w elsa")
                chipsArray.forEach { chip ->
                    Timber.d("chipek")
                    viewModel.fishFilter.speciesList.forEach {species ->
                        Timber.d("${chip.text} vs ${species}")
                        if(chip.text.toString() == species) {
                            chip.isChecked = true
                        }
                    }
                }
            }

        }
    }

    private fun setupDialogs() {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.dateFromInput.setEndIconOnClickListener {
            // Respond to end icon presses
            context?.let { it1 ->
                DatePickerDialog(it1, { view, year, month, dayOfMonth ->
                    binding.dateFromEdittext.setText(
                            "${if (dayOfMonth < 0) "0" else ""}${dayOfMonth}-${if (month < 0) "0" else ""}${month + 1}-${year}"
                    )
                }, year, month, day)
            }?.show()
        }

        binding.dateToInput.setEndIconOnClickListener {
            // Respond to end icon presses
            context?.let { it1 ->
                DatePickerDialog(it1, { view, year, month, dayOfMonth ->
                    binding.dateToEdittext.setText(
                            "${if (dayOfMonth < 0) "0" else ""}${dayOfMonth}-${if (month < 0) "0" else ""}${month + 1}-${year}"
                    )
                }, year, month, day)
            }?.show()
        }
    }

    private fun setupButtons() {
        binding.applyButton.setOnClickListener {
            viewModel.fishFilter.apply {
                if (binding.weightFromEdittext.text.toString().isNotEmpty())
                    weightFrom = binding.weightFromEdittext.text.toString().toFloat()
                else
                    weightFrom = 0.0f
                if (binding.weightToEdittext.text.toString().isNotEmpty())
                    weightTo = binding.weightToEdittext.text.toString().toFloat()
                else
                    weightTo = Float.MAX_VALUE
                if (binding.lengthFromEdittext.text.toString().isNotEmpty())
                    lengthFrom = binding.lengthFromEdittext.text.toString().toInt()
                else
                    lengthFrom = 0
                if (binding.lengthToEdittext.text.toString().isNotEmpty())
                    lengthTo = binding.lengthToEdittext.text.toString().toInt()
                else
                    lengthTo = Int.MAX_VALUE
                val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY)
                if (binding.dateFromEdittext.text.toString().isNotEmpty()) {
                    try {
                        val date = sdf.parse(binding.dateFromEdittext.text.toString())
                        timeTo = date.time
                    } catch (e: ParseException) {
                        timeFrom = 0L
                    }
                    Timber.d(" time from: ${timeFrom}")
                } else {
                    timeFrom = 0L
                }
                if (binding.dateToEdittext.text.toString().isNotEmpty()) {
                    try {
                        val date = sdf.parse(binding.dateToEdittext.text.toString())
                        timeTo = date.time + 86_400_000
                        // adding one day offset just to show all fishes from <start, end>
                    } catch (e: ParseException) {
                        timeTo = Long.MAX_VALUE
                    }
                    Timber.d(" time to: ${timeTo}")
                } else {
                    timeTo = Long.MAX_VALUE
                }

                speciesList = if (chipsArray[0].isChecked) {
                    chipsArray.map { it.text.toString() }
                } else {
                    chipsArray.filter { it.isChecked }.map { it.text.toString() }
                }
            }
            viewModel.load()
            findNavController().navigateUp()
        }

        binding.resetButton.setOnClickListener {
            chipsArray.forEach {
                it.isChecked = false
            }
            binding.chip0.isChecked = true

            binding.apply {
                dateFromEdittext.setText("")
                dateToEdittext.setText("")
                weightFromEdittext.setText("")
                weightToEdittext.setText("")
                lengthFromEdittext.setText("")
                lengthToEdittext.setText("")
            }
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupChips() {
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
    }

    private fun onChipChecked(chip: CompoundButton, isChecked: Boolean) {
        if (chip.id == R.id.chip0) {
            if (isChecked) {
                chipsArray[1].isChecked = false
                chipsArray[2].isChecked = false
                chipsArray[3].isChecked = false
                chipsArray[4].isChecked = false
                chipsArray[5].isChecked = false
            }
        } else {
            if (isChecked)
                chipsArray[0].isChecked = false
        }
    }
}