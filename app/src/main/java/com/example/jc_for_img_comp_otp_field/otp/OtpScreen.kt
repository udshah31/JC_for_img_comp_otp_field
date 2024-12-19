package com.example.jc_for_img_comp_otp_field.otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jc_for_img_comp_otp_field.ui.theme.Green

@Composable
fun OtpScreen(
    state: OtpState,
    focusedRequesters: List<FocusRequester>,
    onAction: (OtpAction) -> Unit,
    modifier: Modifier = Modifier
){

    Column (
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row (modifier = Modifier
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
            ){
            state.code.forEachIndexed { index, number ->
                OtpInputField(
                    number = number,
                    focusRequester = focusedRequesters[index],
                    onFocusChanged = { isFocused ->
                        if (isFocused){
                            onAction(OtpAction.OnChangedFocused(index))
                        }
                    },
                    onNumberChanged = { newNumber ->
                        onAction(OtpAction.OnEnterNumber(newNumber,index))
                    },
                    onKeyboardBack = {
                        onAction(OtpAction.onKeyboardBack)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)

                )
            }
        }
        state.isValid?.let { isValid ->
               Text(
                   text = if (isValid) "OTP is valid!" else "OTP is invalid!",
                   color = if (isValid) Green else Color.Red,
                   fontSize = 16.sp
               )

        }
    }

}