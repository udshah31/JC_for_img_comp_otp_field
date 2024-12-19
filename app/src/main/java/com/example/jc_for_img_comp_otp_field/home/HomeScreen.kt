package com.example.jc_for_img_comp_otp_field.home



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jc_for_img_comp_otp_field.Screen


@Composable
fun HomeScreen(navController: NavHostController) {

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
               navController.navigate(Screen.OTPScreen)
            }
        ) {
            Text("Go To OTP Screen")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                navController.navigate(Screen.ImageCompressionScreen)
            }
        ) {
            Text("Go To Image Compression Screen")
        }
    }


}