package com.example.snoozeloo.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.snoozeloo.R
import com.example.snoozeloo.ui.theme.SnoozelooBlue
import com.example.snoozeloo.ui.theme.SnoozelooGray2
import com.example.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun SnoozelooSaveButton(
   modifier: Modifier = Modifier,
   isSaveButtonEnabled: Boolean = true,
   onSaveButtonClicked: () -> Unit,
   @StringRes buttonText:  Int,
   clipAmount: RoundedCornerShape = RoundedCornerShape(8.dp),
   buttonColors: ButtonColors = ButtonColors(
       containerColor = SnoozelooBlue,
       contentColor = MaterialTheme.colorScheme.background,
       disabledContainerColor = SnoozelooGray2,
       disabledContentColor = MaterialTheme.colorScheme.background
   )
) {
    Button(
        modifier = modifier
            .clip(clipAmount),
        colors = buttonColors,
        enabled = isSaveButtonEnabled,
        onClick = { onSaveButtonClicked() }
    ) {
        Text(
            text = stringResource(buttonText),
            style = MaterialTheme.typography.bodySmall
        )
    }

}


@Preview
@Composable
private fun SnoozelooSaveButtonPreview() = SnoozelooTheme {
    SnoozelooSaveButton(
        onSaveButtonClicked = {},
        buttonText = R.string.save
    )
}