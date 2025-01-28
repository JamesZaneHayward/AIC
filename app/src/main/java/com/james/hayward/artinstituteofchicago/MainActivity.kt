package com.james.hayward.artinstituteofchicago

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.james.hayward.artinstituteofchicago.ui.AicApp
import com.james.hayward.artinstituteofchicago.ui.theme.ArtInstituteOfChicagoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtInstituteOfChicagoTheme {
                AicApp()
            }
        }
    }
}