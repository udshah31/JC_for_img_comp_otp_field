package com.example.jc_for_img_comp_otp_field

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jc_for_img_comp_otp_field.otp.OtpAction
import com.example.jc_for_img_comp_otp_field.otp.OtpScreen
import com.example.jc_for_img_comp_otp_field.otp.OtpViewModel
import com.example.jc_for_img_comp_otp_field.ui.theme.JC_for_img_comp_otp_fieldTheme
import com.example.jc_for_img_comp_otp_field.ui.theme.Pink80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC_for_img_comp_otp_fieldTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize(),
                    contentColor = Pink80
                ) { innerPadding ->
                    val viewModel = viewModel<OtpViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val focusRequesters = remember { List(4) {FocusRequester()} }
                    val focusManager = LocalFocusManager.current
                    val keyboardManager = LocalSoftwareKeyboardController.current
                    LaunchedEffect(state.focusedIndex) {
                        state.focusedIndex?.let { index ->
                            focusRequesters.getOrNull(index)?.requestFocus()
                        }
                    }

                    LaunchedEffect(state.code, keyboardManager) {
                        val allNumberEntered = state.code.none{it == null}
                        if (allNumberEntered){
                            focusRequesters.forEach {
                                it.freeFocus()
                            }
                            focusManager.clearFocus()
                            keyboardManager?.hide()
                        }
                    }
                   OtpScreen(
                       state = state,
                       focusedRequesters = focusRequesters,
                       onAction = { action ->
                           when(action){
                               is OtpAction.OnEnterNumber -> {
                                   if (action.number != null){
                                       focusRequesters[action.index].freeFocus()
                                   }
                               }
                               else -> Unit
                           }
                           viewModel.onAction(action)
                       },
                       modifier = Modifier
                           .padding(innerPadding)
                           .consumeWindowInsets(innerPadding)
                   )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JC_for_img_comp_otp_fieldTheme {
        Greeting("Android")
    }
}