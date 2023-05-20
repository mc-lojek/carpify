package pl.mclojek.carpify.presentation.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import pl.mclojek.carpify.domain.model.FishSpecies

@Composable
fun SpeciesPicker(
    selectedSpecies: FishSpecies,
    onSpeciesSelected: (FishSpecies) -> Unit,
    speciesList: List<FishSpecies>
) {
    var showDialog by remember { mutableStateOf(false) }
    Text(
        modifier = Modifier
            .clickable {
                showDialog = true
            }
            .padding(start = 20.dp, end = 5.dp),
        text = selectedSpecies.name
    )

    if (showDialog)
        SpeciesPickerDialog(speciesList, onSpeciesSelected) {
            showDialog = false
        }
}

@Composable
fun SpeciesPickerDialog(
    speciesList: List<FishSpecies>,
    onSelection: (FishSpecies) -> Unit,
    dismiss: () -> Unit,
) {
    Dialog(onDismissRequest = dismiss) {
        Box {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 40.dp)
                    .background(shape = RoundedCornerShape(20.dp), color = Color.White)
            ) {
                for (species in speciesList) {
                    item {
                        Text(
                            modifier = Modifier
                                .clickable {
                                    onSelection(species)
                                    dismiss()
                                }
                                .fillMaxWidth()
                                .padding(10.dp),
                            text = species.name
                        )
                    }
                }
            }
        }
    }
}