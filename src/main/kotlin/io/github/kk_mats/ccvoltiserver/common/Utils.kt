package io.github.kk_mats.ccvoltiserver.common

fun <T : Any?> orUndefined(value: T) = value ?: "undefined"