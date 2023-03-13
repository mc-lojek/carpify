package pl.mclojek.carpify.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.mclojek.carpify.R

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
fun SearchField(
    modifier: Modifier = Modifier,
    searchText: String,
    keyboardController: SoftwareKeyboardController?,
    onHideClicked: () -> Unit,
    action: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .padding(start = 4.dp)
            .fillMaxWidth(),
        onValueChange = { action(it) },
        value = searchText,
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        leadingIcon = {
            IconButton(onClick = { onHideClicked() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Search icon")
            }
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = searchText != "",
                enter = fadeIn(),
                exit = fadeOut()
            )
            {
                IconButton(onClick = {
                    keyboardController?.hide()
                    action("")
                }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear search text"
                    )
                }
            }
        },
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
private fun DoPreview() {
    SearchField(
        searchText = "Heia",
        keyboardController = null,
        onHideClicked = {},
        action = {},
    )
}