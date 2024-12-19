package com.example.jc_for_img_comp_otp_field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jc_for_img_comp_otp_field.home.HomeScreen
import com.example.jc_for_img_comp_otp_field.image_compress.FileManager
import com.example.jc_for_img_comp_otp_field.image_compress.ImageCompressor
import com.example.jc_for_img_comp_otp_field.image_compress.PhotoPickerScreen
import com.example.jc_for_img_comp_otp_field.otp.OtpAction
import com.example.jc_for_img_comp_otp_field.otp.OtpScreen
import com.example.jc_for_img_comp_otp_field.otp.OtpViewModel
import kotlinx.serialization.Serializable

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    val viewModel = viewModel<OtpViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusRequesters = remember { List(4) { FocusRequester() } }
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current
    LaunchedEffect(state.focusedIndex) {
        state.focusedIndex?.let { index ->
            focusRequesters.getOrNull(index)?.requestFocus()
        }
    }

    LaunchedEffect(state.code, keyboardManager) {
        val allNumberEntered = state.code.none { it == null }
        if (allNumberEntered) {
            focusRequesters.forEach {
                it.freeFocus()
            }
            focusManager.clearFocus()
            keyboardManager?.hide()
        }
    }
    val context = LocalContext.current


    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen
    ) {
        composable<Screen.HomeScreen> {
            HomeScreen(navController)
        }

        composable<Screen.OTPScreen> {
            OtpScreen(
                state = state,
                focusedRequesters = focusRequesters,
                onAction = {
                        action ->
                    when (action) {
                        is OtpAction.OnEnterNumber -> {
                            if (action.number != null) {
                                focusRequesters[action.index].freeFocus()
                            }
                        }

                        else -> Unit
                    }
                    viewModel.onAction(action)
                },
            )
        }

        composable<Screen.ImageCompressionScreen> {
            PhotoPickerScreen(
                imageCompressor = ImageCompressor(context),
                fileManager = FileManager(context)
            )
        }
    }

}


@Serializable
sealed class Screen {
    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data object OTPScreen : Screen()

    @Serializable
    data object ImageCompressionScreen : Screen()

}