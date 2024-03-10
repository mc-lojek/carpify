package pl.mclojek.carpify.presentation.dialog

import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.time.LocalTime

@Composable
fun LocalTimePicker(
    selectedTime: LocalTime,
    onTimeSelected: (LocalTime) -> Unit,
) {

    val timePickerValue = android.app.TimePickerDialog(
        LocalContext.current,
        { _: TimePicker, hour: Int, minute: Int ->
            onTimeSelected(LocalTime.of(hour, minute))
        },
        selectedTime.hour,
        selectedTime.minute,
        true
    )

    Text(
        modifier = Modifier
            .clickable {
                timePickerValue.show()
            }
            .padding(start = 20.dp, end = 5.dp),
        text = selectedTime.toString()
    )
}