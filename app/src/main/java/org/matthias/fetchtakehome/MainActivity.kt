package org.matthias.fetchtakehome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.matthias.fetchtakehome.screen.hiring.HiringScreen
import org.matthias.fetchtakehome.ui.theme.FetchTakeHomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchTakeHomeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HiringScreen(modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize())
                }
            }
        }
    }
}