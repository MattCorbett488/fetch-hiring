package org.matthias.fetchtakehome.screen.hiring

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HireRetry(text: String, onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Center, modifier = modifier) {
        Text(text = text)
        OutlinedButton(onClick = onRetry) { Text(text = "Retry") }
    }
}

@Preview(showBackground = true)
@Composable
fun HireRetryPreview() {
    var retryCount by remember { mutableIntStateOf(0) }

    Column {
        HireRetry(
            text = "Hire retry text",
            onRetry = { retryCount += 1 }
        )
        Text(text = "Retried $retryCount times")
    }
}