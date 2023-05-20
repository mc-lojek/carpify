package pl.mclojek.carpify.presentation.screen

import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.mclojek.carpify.domain.model.AllSpecies
import pl.mclojek.carpify.presentation.dialog.LocalDatePicker
import pl.mclojek.carpify.presentation.dialog.SpeciesPicker
import pl.mclojek.carpify.presentation.util.SuffixTransformation
import pl.mclojek.carpify.presentation.viewmodel.AddFishViewModel
import java.time.LocalDate
import java.time.LocalTime
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun AddFishScreen(navigator: DestinationsNavigator) {

    val vm: AddFishViewModel = hiltViewModel()

    var speciesState by remember { mutableStateOf("") }
    var weightState by remember { mutableStateOf("") }
    var lengthState by remember { mutableStateOf("") }
    var catchDateState by remember { mutableStateOf(LocalDate.now()) }
    var catchTimeState by remember { mutableStateOf(LocalTime.now()) }
    var descriptionState by remember { mutableStateOf("") }

    val datePickerValue = android.app.DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            catchDateState = LocalDate.of(year, month + 1, day)
        },
        catchDateState.year,
        catchDateState.monthValue - 1,
        catchDateState.dayOfMonth
    )

    val timePickerValue = android.app.TimePickerDialog(
        LocalContext.current,
        { _: TimePicker, hour: Int, minute: Int ->
            catchTimeState = LocalTime.of(hour, minute)
        },
        catchTimeState.hour,
        catchTimeState.minute,
        true
    )

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
                TextField(
                    modifier = Modifier
                        .clickable { datePickerValue.show() }
                        .fillMaxWidth(),
                    value = catchDateState.toString(),
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    label = { Text("Catch date") }
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { timePickerValue.show() },
                    value = catchTimeState.toString(),
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    label = { Text("Catch time") }
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = descriptionState,
                    onValueChange = { descriptionState = it },
                    label = { Text("Description") }
                )
            }
        })
}

fun processFloat(input: String): String {

    val allowedCharacters = "1234567890,".toList()
    var output = input.replace(".", ",")
    output = output.filter { allowedCharacters.contains(it) }

    return output
}