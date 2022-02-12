package pl.mclojek.carpify.presentation.fragment

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import pl.mclojek.carpify.R
import pl.mclojek.carpify.databinding.FragmentAddFishBinding
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.presentation.viewmodel.FishMapViewModel
import timber.log.Timber
import java.util.*
import kotlin.random.Random.Default.nextInt

class AddFishFragment : Fragment(), KodeinAware {

    private val PICK_IMAGE = 2021

    override val kodein: Kodein by closestKodein()
    private lateinit var binding: FragmentAddFishBinding
    private val viewModel: FishMapViewModel by instance()
    private var chipsArray = emptyArray<Chip>()

    private var imageUri: Uri? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddFishBinding.inflate(inflater, container, false)

        setupViews()
        setupButtons()
        setupDialogs()
        setupObservers()

        return binding.root
    }

    private fun setupViews() {
        chipsArray = arrayOf(
                binding.chip1,
                binding.chip2,
                binding.chip3,
                binding.chip4,
                binding.chip5,
        )
    }

    private fun setupButtons() {
        binding.selectCoordsButton.setOnClickListener {
            findNavController().navigate(R.id.select_coords_fragment)
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.confirmButton.setOnClickListener {
            Timber.d("cokolwiek")

            if(chipsArray.all { !it.isChecked } ) {
                Toast.makeText(activity, "Wybierz gatunek!", Toast.LENGTH_SHORT).show()
            }
            else if(binding.weightEdittext.text.isNullOrBlank()) {
                Toast.makeText(activity, "Podaj wagę!", Toast.LENGTH_SHORT).show()
            }
            else if(binding.lengthEdittext.text.isNullOrBlank()) {
                Toast.makeText(activity, "Podaj długość!", Toast.LENGTH_SHORT).show()
            }
            else if(binding.dateEdittext.text.isNullOrBlank()) {
                Toast.makeText(activity, "Wybierz datę!", Toast.LENGTH_SHORT).show()
            }
            else if(binding.timeEdittext.text.isNullOrBlank()) {
                Toast.makeText(activity, "Wybierz godzinę!", Toast.LENGTH_SHORT).show()
            }
            else if(binding.selectCoordsButton.text.toString() == getString(R.string.select_coords)) {
                Toast.makeText(activity, "Wybierz pozycję!", Toast.LENGTH_SHORT).show()
            }
            else {
                addFish()
            }
        }

        binding.image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png", "image/jpg")
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivityForResult(intent, PICK_IMAGE)

        }
    }

    private fun setupDialogs() {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.dateInput.setEndIconOnClickListener {
            // Respond to end icon presses
            context?.let { it1 ->
                DatePickerDialog(it1, { view, year, month, dayOfMonth ->
                    binding.dateEdittext.setText(
                            "${if (dayOfMonth < 10) "0" else ""}${dayOfMonth}-${if (month < 10) "0" else ""}${month + 1}-${year}"
                    )
                }, year, month, day)
            }?.show()
        }

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        binding.timeInput.setEndIconOnClickListener {
            // Respond to end icon presses
            context?.let { it1 ->
                TimePickerDialog(it1, { view, hour, minute ->
                    binding.timeEdittext.setText(
                            "${if (hour < 10) "0" else ""}${hour} : ${if (minute < 10) "0" else ""}${minute}"
                    )
                }, hour, minute, true)
            }?.show()
        }
    }

    private fun setupObservers() {
        viewModel.newFishPosObservable.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.selectCoordsButton.setText(String.format("%.6f, %.6f", it.latitude, it.longitude))
            }
        }
    }

    private fun addFish() {

        val c = Calendar.getInstance()
        val splittedDate = binding.dateEdittext.text.toString().split("-").map { it.trim().toInt() }
        val splittedTime = binding.timeEdittext.text.toString().split(":").map { it.trim().toInt() }
        c.set(splittedDate[2], splittedDate[1], splittedDate[0], splittedTime[0], splittedTime[1])


        binding.apply {
            val fish = Fish(
                    Random().nextInt(),
                    chipsArray.first { it.isChecked }.text.toString(),
                    weightEdittext.text.toString().toFloat(),
                    lengthEdittext.text.toString().toInt(),
                    c.timeInMillis,
                    baitEdittext.text.toString(),
                    descEdittext.text.toString(),
                    selectCoordsButton.text.toString(),
                    viewModel.lake?.id ?: -1,
                    1,
                    imageUri.toString() ?: "pusto"
            )
            viewModel.addFish(fish)
            findNavController().navigateUp()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            PICK_IMAGE -> {
                if(resultCode == Activity.RESULT_OK) {
                    data?.data?.let {
                        launchImageCrop(it)
                    }
                } else {
                    Timber.w("Image selection error, Can't select this image!")
                }
            }
            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                val result = CropImage.getActivityResult(data)
                if(resultCode == Activity.RESULT_OK) {
                    result.uri.let {
                        Picasso.get().load(it)
                            .fit()
                            .into(binding.image)
                        Timber.d("Uri: $it}")
                        imageUri = it
                    }
                } else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Timber.w("Cropping error! ${result.error}")
                }
            }
        }
    }

    private fun launchImageCrop(uri: Uri) {
        context?.let {
            CropImage.activity(uri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1280, 720)
                .start(it, this)
        }
    }
}