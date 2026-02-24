package com.android.realestatete.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.realestatete.presentation.ui.PropertyScreenRoute
import com.android.realestatete.presentation.ui.theme.RealEstateTestAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RealEstateTestAndroidTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    PropertyScreenRoute()
//                }
                PropertyScreenRoute()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RealEstateTestAndroidTheme {
        PropertyScreenRoute()
    }
}