package pl.mclojek.carpify.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.LatLng
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import pl.mclojek.carpify.domain.model.AllSpecies
import pl.mclojek.carpify.presentation.dialog.LocalDatePicker
import pl.mclojek.carpify.presentation.dialog.LocalTimePicker
import pl.mclojek.carpify.presentation.dialog.SpeciesPicker
import pl.mclojek.carpify.presentation.screen.destinations.CoordsSelectionScreenDestination
import pl.mclojek.carpify.presentation.util.SuffixTransformation
import pl.mclojek.carpify.presentation.viewmodel.AddFishViewModel
import java.time.LocalDate
import java.time.LocalTime
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun AddFishScreen(
    navigator: DestinationsNavigator,
    resultRecipient: ResultRecipient<CoordsSelectionScreenDestination, LatLng>
) {

    val vm: AddFishViewModel = hiltViewModel()

    var speciesState by remember { mutableStateOf("") }
    var weightState by remember { mutableStateOf("") }
    var lengthState by remember { mutableStateOf("") }
    var catchDateState by remember { mutableStateOf(LocalDate.now()) }
    var catchTimeState by remember { mutableStateOf(LocalTime.now()) }
    var descriptionState by remember { mutableStateOf("") }



    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = "Add fish")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigator.navigateUp()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            vm.addFish("1", Random.nextFloat())
                            navigator.navigateUp()
                        }) {
                        Icon(Icons.Filled.Done, null)
                    }
                },
            )
        }, content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                SpeciesPicker(
                    selectedSpecies = AllSpecies.first(),
                    onSpeciesSelected = {},
                    speciesList = AllSpecies
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = speciesState,
                    onValueChange = { speciesState = it },
                    label = { Text("Species") },
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = weightState,
                    onValueChange = { weightState = processFloat(it) },
                    label = { Text("Weight") },
                    visualTransformation = SuffixTransformation(" kg"),
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = lengthState,
                    onValueChange = { lengthState = it },
                    label = { Text("Length") },
                    visualTransformation = SuffixTransformation(" cm"),
                )
                LocalDatePicker(
                    selectedDate = catchDateState,
                    onDateSelected = { catchDateState = it }
                )
                LocalTimePicker(
                    selectedTime = catchTimeState,
                    onTimeSelected = { catchTimeState = it })
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = descriptionState,
                    onValueChange = { descriptionState = it },
                    label = { Text("Description") }
                )
                Button(onClick = {navigator.navigate(CoordsSelectionScreenDestination)}) {
                    Text("Coords")
                }
            }
        })

    resultRecipient.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {
                // `GoToProfileConfirmationDestination` was shown but it was canceled
                // and no value was set (example: dialog/bottom sheet dismissed)
            }

            is NavResult.Value -> {
                println("result received from GoToProfileConfirmationDestination = ${result.value}")
                // Do whatever with the result received!
                // Think of it like a button click, usually you want to call
                // a view model method here or navigate somewhere
            }
        }
    }
}

fun processFloat(input: String): String {

    val allowedCharacters = "1234567890,".toList()
    var output = input.replace(".", ",")
    output = output.filter { allowedCharacters.contains(it) }

    return output
}