package com.example.snoozeloo.presentation.triggerAlarm

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R
import com.example.snoozeloo.ui.theme.AlarmIcon
import com.example.snoozeloo.ui.theme.SnoozelooBlue
import com.example.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun TriggerAlarmScreen(
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Icon(
            imageVector = AlarmIcon,
            contentDescription = stringResource(R.string.alarm_icon),
            tint = SnoozelooBlue
        )

        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "10:00",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 76.sp,
                color = SnoozelooBlue
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "WORK",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(
                containerColor = SnoozelooBlue,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .wrapContentWidth()
                .height(48.dp)
        ) {
            Text(
                text = stringResource(R.string.turn_off),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }

    }
}

@Composable
@Preview
fun TriggerAlarmScreenPreview() = SnoozelooTheme {
    TriggerAlarmScreen()
}

