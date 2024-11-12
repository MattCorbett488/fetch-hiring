package org.matthias.fetchtakehome.ui.composables.hiring

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HireItem(name: String, modifier: Modifier = Modifier) {
    Text(text = name, modifier = modifier)
}