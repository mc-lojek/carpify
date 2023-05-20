package pl.mclojek.carpify.presentation.dialog

import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun LocalDatePicker(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
) {

    val datePickerValue = android.app.DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            onDateSelected(LocalDate.of(year, month + 1, day))
        },
        selectedDate.year,
        selectedDate.monthValue - 1,
        selectedDate.dayOfMonth
    )

    Text(
        modifier = Modifier
            .clickable {
                datePickerValue.show()
            }
            .padding(start = 20.dp, end = 5.dp),
        text = selectedDate.toString()
    )
}