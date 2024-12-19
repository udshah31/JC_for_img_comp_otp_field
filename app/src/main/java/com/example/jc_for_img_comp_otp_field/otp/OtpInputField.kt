package com.example.jc_for_img_comp_otp_field.otp

import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.jc_for_img_comp_otp_field.ui.theme.Green
import com.example.jc_for_img_comp_otp_field.ui.theme.JC_for_img_comp_otp_fieldTheme
import com.example.jc_for_img_comp_otp_field.ui.theme.LightGray
import com.example.jc_for_img_comp_otp_field.ui.theme.Pink40
import com.example.jc_for_img_comp_otp_field.ui.theme.Purple40
import com.example.jc_for_img_comp_otp_field.ui.theme.PurpleGrey80

@Composable
fun OtpInputField(
    number:Int?,
    focusRequester: FocusRequester,
    onFocusChanged:(Boolean) -> Unit,
    onNumberChanged: (Int?) -> Unit,
    onKeyboardBack: () -> Unit,
    modifier: Modifier = Modifier
){
    val text by remember { mutableStateOf(
        TextFieldValue(
            text = number?.toString().orEmpty(),
            selection = TextRange(
                index = if (number != null) 1 else 0
            )
        )
    )
    }

    var isFocused by remember { mutableStateOf(false) }

   Box(
       modifier = modifier
           .border(
               width = 1.dp,
               color = Green
           )
           .background(LightGray),
       contentAlignment = Alignment.Center
   ){
       BasicTextField(
           value = text,
           onValueChange = {newText ->
               val newNumber = newText.text
               if(newNumber.length <= 1 && newNumber.isDigitsOnly()){
                   onNumberChanged(newNumber.toIntOrNull())
               }
           },
           cursorBrush = SolidColor(Green),
           singleLine = true,
           textStyle = TextStyle(
               textAlign = TextAlign.Center,
               fontWeight = FontWeight.Light,
               fontSize = 36.sp
           ),
           keyboardOptions = KeyboardOptions(
               keyboardType =  KeyboardType.Number
           ),
           modifier = Modifier
               .padding(10.dp)
               .focusRequester(focusRequester)
               .onFocusChanged {
                   isFocused = it.isFocused
                   onFocusChanged(it.isFocused)
               }
               .onKeyEvent { event ->
                   val didPressDelete = event.nativeKeyEvent.keyCode ==KeyEvent.KEYCODE_DEL
                   if (didPressDelete && number == null){
                       onKeyboardBack()
                   }
                   false
               },
           decorationBox = { innerBox ->
               innerBox()
               if(isFocused && number == null){
                   Text(
                       text = "_",
                       textAlign = TextAlign.Center,
                       color = Green,
                       fontSize = 36.sp,
                       fontWeight = FontWeight.Light,
                       modifier = Modifier
                           .fillMaxSize()
                           .wrapContentSize()
                   )
               }
           }
       )
   }




}

@Preview
@Composable
private fun OtpInputFieldPreview(){
    JC_for_img_comp_otp_fieldTheme{
        OtpInputField(
            number = null,
            focusRequester = remember { FocusRequester() },
            onFocusChanged = {},
            onKeyboardBack = {},
            onNumberChanged = {},
            modifier = Modifier.size(100.dp)

        )
    }
}