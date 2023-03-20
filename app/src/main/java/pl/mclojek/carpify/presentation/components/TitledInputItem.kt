package pl.mclojek.carpify.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitledInputItem(title: String, inputText: String) {
    Column {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )
        BasicTextField(
            enabled = false,
            value = inputText,
            onValueChange = {},
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            textStyle = TextStyle(color = MaterialTheme.colorScheme.secondary, fontSize = 14.sp)
        )
        Divider(modifier = Modifier.padding(horizontal = 8.dp))
    }
}
