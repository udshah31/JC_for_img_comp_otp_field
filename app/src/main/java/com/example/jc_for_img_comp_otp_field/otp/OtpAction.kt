package com.example.jc_for_img_comp_otp_field.otp

sealed interface OtpAction {
    data class OnEnterNumber(val number:Int?, val index:Int):OtpAction
    data class OnChangedFocused(val index:Int):OtpAction
    data object onKeyboardBack: OtpAction
}