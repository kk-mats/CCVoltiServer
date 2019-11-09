package io.github.kk_mats.ccvoltiserver.domain.type.query

import io.github.kk_mats.ccvoltiserver.domain.type.Label

data class RawDetectionQuery(val target: String, val output: String, val parameters: HashMap<String, String>)

data class DetectionQuery(
		val target: String,
		val output: String,
		val version: Label,
		val parameters: HashMap<Label, String>
)