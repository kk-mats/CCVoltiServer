package io.github.kk_mats.ccvoltiserver.domain.type.response

data class DetectionResponse(val id: String, val output: String, val additions: List<String>, val log: String)