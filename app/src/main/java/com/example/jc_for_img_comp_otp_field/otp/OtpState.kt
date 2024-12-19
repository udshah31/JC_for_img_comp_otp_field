package com.example.jc_for_img_comp_otp_field.otp

data class OtpState(
    val code: List<Int?> = (1..4).map { null },
    val focusedIndex: Int? = null,
    val isValid: Boolean? = null
){

}