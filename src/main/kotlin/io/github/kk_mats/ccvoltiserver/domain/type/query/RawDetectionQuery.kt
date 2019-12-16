package io.github.kk_mats.ccvoltiserver.domain.type.query

import io.github.kk_mats.ccvoltiserver.domain.type.Label

data class Directory(val relative: String, val absolute: String)

data class Revision(val branch: String, val commitId: String)

data class Target(val directory: Directory, val revision: Revision)

data class RawDetectionQuery(val target: Target, val output: String, val parameters: HashMap<String, String>)

data class DetectionQuery(
		val target: Target,
		val output: String,
		val version: Label,
		val parameters: HashMap<Label, String>
)