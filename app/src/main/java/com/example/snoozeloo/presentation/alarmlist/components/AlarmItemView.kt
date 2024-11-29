package com.example.snoozeloo.presentation.alarmlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.snoozeloo.core.extensions.toSafeString
import com.example.snoozeloo.presentation.model.AlarmUi
import com.example.snoozeloo.ui.theme.SnoozelooBlue
import com.example.snoozeloo.ui.theme.SnoozelooGray
import com.example.snoozeloo.ui.theme.SnoozelooOpenBlue
import com.example.snoozeloo.ui.theme.SnoozelooTheme
import com.example.snoozeloo.ui.theme.SnoozelooWhite

@Composable
fun AlarmItemView(
    modifier: Modifier = Modifier,
    alarmToggleChanged: (Int, Boolean) -> Unit,
    alarmUi: AlarmUi
) {
    Column (
       modifier = modifier
           .fillMaxWidth()
           .clip(RoundedCornerShape(16.dp))
           .background(Color.White)
           .padding(16.dp)
    ){

      Row(
         modifier = modifier
             .fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically
      ) {
          Text(
              text = alarmUi.name.toSafeString(),
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onBackground,
          )

          Spacer(modifier = Modifier.weight(1f))

          Switch(
              checked = alarmUi.isEnabled,
              onCheckedChange = { alarmToggleStateChanged->
                   alarmToggleChanged(alarmUi.id, alarmToggleStateChanged)
              },
              colors = SwitchDefaults.colors(
                  checkedThumbColor = SnoozelooWhite,
                  checkedTrackColor = SnoozelooBlue,
                  uncheckedThumbColor = SnoozelooWhite,
                  uncheckedTrackColor = SnoozelooOpenBlue,
                  uncheckedBorderColor = SnoozelooOpenBlue
              )
          )
      }


        Text(
            text = alarmUi.formattedAlarmTime,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = alarmUi.formattedRemainingTime,
            style = MaterialTheme.typography.bodySmall,
            color = SnoozelooGray
        )
    }

}

@Preview
@Composable
fun AlarmItemViewPreview() = SnoozelooTheme {
    AlarmItemView(
        alarmToggleChanged = { id,isEnabled ->

        },
        alarmUi = AlarmUi(
            id = 1,
            name =  "Wake Up",
            formattedAlarmTime = "10:00 AM",
            formattedRemainingTime = "Alarm in 30min"
        )
    )
}