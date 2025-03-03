package com.example.completereadbookapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.completereadbookapp.ui.theme.CompleteReadBookAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompleteReadBookAppTheme {
                ComicApp()
            }
        }
    }
}



@Preview
@Composable
fun ComicAppPreview(){
    ComicApp()
}
