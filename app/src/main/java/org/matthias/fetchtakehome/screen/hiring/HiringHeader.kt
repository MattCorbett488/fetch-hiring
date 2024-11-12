package org.matthias.fetchtakehome.screen.hiring

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HiringHeader(header: String, modifier: Modifier = Modifier) {
    Text(
        text = header,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HiringHeaderPreview() {
    HiringHeader(
        header = "A header",
        modifier = Modifier.padding(32.dp)
    )
}