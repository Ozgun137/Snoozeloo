package com.example.snoozeloo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun SnoozelooPermissionDialog(
    title:String,
    onDismiss:()->Unit,
    description: String,
    modifier: Modifier = Modifier,
    dialogPrimaryButton: @Composable RowScope.() -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Column (
            modifier = modifier
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = description,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                dialogPrimaryButton()
            }
        }
    }
}

@Preview
@Composable
private fun SnoozelooPermissionDialogPreview() = SnoozelooTheme {
   SnoozelooPermissionDialog(
       title = "Permission required",
       onDismiss = {},
       description = "In order to notify you when an alarm goes off, this app needs your permission for that. Please click on Okay to Grant it",
       dialogPrimaryButton = {}
   )
}