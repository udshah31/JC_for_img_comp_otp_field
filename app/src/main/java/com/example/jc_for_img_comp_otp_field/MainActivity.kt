package com.example.jc_for_img_comp_otp_field

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.jc_for_img_comp_otp_field.ui.theme.JC_for_img_comp_otp_fieldTheme
import com.example.jc_for_img_comp_otp_field.ui.theme.Pink80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC_for_img_comp_otp_fieldTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentColor = Pink80,
                ) { innerPadding ->

                    Navigation(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}
