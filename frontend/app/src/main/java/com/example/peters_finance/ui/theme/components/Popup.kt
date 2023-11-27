package com.example.peters_finance.ui.theme.components

import StyledButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peters_finance.ui.theme.CustomTheme

@Composable
fun Popup(
    message: String,
    onDismiss: () -> Unit
) {
    CustomTheme {
        Dialog(onDismissRequest = { onDismiss() }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = message,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    StyledButton(text = "Dismiss", onClick = { onDismiss })
                }
            }
        }
    }
}
