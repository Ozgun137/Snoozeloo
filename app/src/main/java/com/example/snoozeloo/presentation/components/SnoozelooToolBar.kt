package com.example.snoozeloo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.snoozeloo.R
import com.example.snoozeloo.ui.theme.SnoozelooBlue
import com.example.snoozeloo.ui.theme.SnoozelooTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnoozelooToolBar (
    showCancelButton: Boolean = false,
    isCancelButtonEnabled: Boolean = true,
    showSaveButton: Boolean = false,
    isSaveButtonEnabled: Boolean = true,
    title: String = "",
    modifier: Modifier = Modifier,
    onCancelButtonClick: () -> Unit = {},
    onSaveButtonClick: () -> Unit = {},
)
{
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        },
        navigationIcon = {
            if(showCancelButton) {
                SnoozelooCancelButton(
                    modifier = modifier,
                    isEnabled = isCancelButtonEnabled,
                    onCancelClicked = onCancelButtonClick,
                    icon = Icons.Default.Close,
                    backgroundColor = SnoozelooBlue,
                    tintColor = Color.White
                )
            }
        },

        actions = {
            if(showSaveButton) {
                SnoozelooSaveButton(
                    modifier = Modifier.padding(16.dp),
                    isSaveButtonEnabled = isSaveButtonEnabled,
                    onSaveButtonClicked = onSaveButtonClick,
                    buttonText = R.string.save
                )
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun SnoozelooToolBarPreview() = SnoozelooTheme {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )
    SnoozelooToolBar(
        showCancelButton = true
    )
}