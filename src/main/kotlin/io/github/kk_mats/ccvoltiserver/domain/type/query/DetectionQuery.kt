package io.github.kk_mats.ccvoltiserver.domain.type.query

data class DetectionQuery(val location: String, val parameters: HashMap<String, String>)