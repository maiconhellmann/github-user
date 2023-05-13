package com.maiconhellmann.shared.extensions


fun Int?.orZero() = this ?: 0
fun Long?.orZero() = this ?: 0L
fun Boolean?.orFalse() = this ?: false
