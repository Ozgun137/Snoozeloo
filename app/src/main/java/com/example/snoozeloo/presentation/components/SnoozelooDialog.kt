package com.example.snoozeloo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.snoozeloo.R
import com.example.snoozeloo.ui.theme.SnoozelooGray2
import com.example.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun SnoozelooDialog(
    modifier: Modifier = Modifier,
    title:String,
    onDismiss:() -> Unit,
    onSaveClicked:(String) -> Unit,
    saveButton: @Composable () -> Unit
) {

    var alarmNameValue by rememberSaveable {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {

            Column (
                modifier = modifier
                    .padding(16.dp)
            ){
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = alarmNameValue,
                    singleLine = true,
                    onValueChange = {
                        alarmNameValue = it
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = SnoozelooGray2,
                        unfocusedBorderColor = SnoozelooGray2,
                    ),
                    textStyle = MaterialTheme.typography.bodySmall
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    saveButton()
                }

            }
        }
    }


}


@Preview
@Composable
private fun SnoozelooDialogPreview() = SnoozelooTheme {
     SnoozelooDialog(
         title = "Alarm Name",
         onDismiss = {},
         onSaveClicked = {},
         saveButton = {
             SnoozelooSaveButton(
                 onSaveButtonClicked = {},
                 buttonText = R.string.save,
             )
         }
     )
}