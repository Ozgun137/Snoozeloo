package com.example.snoozeloo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.snoozeloo.ui.theme.SnoozelooBlue
import com.example.snoozeloo.ui.theme.SnoozelooGray2
import com.example.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun SnoozelooCancelButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    onCancelClicked: () -> Unit,
    contentDescription: String = "",
    size: Dp = 36.dp,
    icon: ImageVector,
    backgroundColor : Color,
    tintColor : Color
) {

    val buttonBackgroundColor = if (isEnabled) backgroundColor else SnoozelooGray2.copy(alpha = 0.75f)

    IconButton(
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
        .size(size)
        .background(buttonBackgroundColor),
        onClick = onCancelClicked
    ) {
        Icon(
            modifier = Modifier
                .size(size / 2),
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tintColor
        )
    }

}


@Preview
@Composable
fun SnoozelooCancelButtonPreview() = SnoozelooTheme {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        SnoozelooCancelButton(
            icon = Icons.Default.Close,
            tintColor = Color.White,
            backgroundColor = SnoozelooBlue,
            onCancelClicked = {},
            isEnabled = false
        )
    }
}