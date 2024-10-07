package com.example.eduardo.tecsa.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eduardo.tecsa.R
import com.example.eduardo.tecsa.lib.util.ConnectionState
import com.example.eduardo.tecsa.lib.util.connectivityState


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBar(modifier: Modifier = Modifier) {
    val connection by connectivityState()

    val isConnected = connection === ConnectionState.Available
    val tooltipState = rememberTooltipState(initialIsVisible = false)

    TopAppBar(title = {
        Row {
            Text(
                text = "trending today",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1F)
            )
            if (!isConnected) {
                TooltipBox(
                    positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                    tooltip = {
                        PlainTooltip(modifier = Modifier.padding(4.dp)) {
                            Text(text = "you are offline")
                        }
                    },
                    state = tooltipState,
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.signal_wifi_off_24px),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(12.dp)
                            .alpha(.3f),
                        tint = Color.Red,
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    })
}