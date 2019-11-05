package io.github.kk_mats.ccvoltiserver.domain.type

object FailureCode {
	fun parameterUndefinedWithoutDefault(parameterNameLabel: String) = Failure(
			"PARAMETER_UNDEFINED_WITHOUT_DEFAULT",
			"@${parameterNameLabel}: undefined without default value"
	)

	fun parameterOutOfRange(parameterNameLabel: String, value: Double, min: Double?, max: Double?) = Failure(
			"PARAMETER_OUT_OF_RANGE",
			"@${parameterNameLabel}: \"$value\" is out of range [${min ?: "undefined"}, ${max ?: "undefined"}]"
	)

	fun invalidRangeParameterType(parameterNameLabel: String, value: String) = Failure(
			"INVALID_RANGE_PARAMETER_TYPE",
			"@${parameterNameLabel}: type of \"$value\" is not acceptable"
	)

	fun invalidParameterRange(parameterNameLabel: String, min: Double?, max: Double?) = Failure(
			"PARAMETER_OUT_OF_RANGE",
			"@${parameterNameLabel}: invalid range [${min ?: "undefined"}, ${max ?: "undefined"}]"
	)

	fun parameterDefaultOutOfRange(parameterNameLabel: String, value: Double, min: Double?, max: Double?) = Failure(
			"PARAMETER_DEFAULT_OUT_OF_RANGE",
			"@${parameterNameLabel}: \"$value\" is out of range [${min ?: "undefined"}, ${max ?: "undefined"}]"
	)

	fun <T> parameterNotContained(parameterNameLabel: String, value: T) = Failure(
			"PARAMETER_NOT_CONTAINED",
			"@${parameterNameLabel}: \"$value\" is not contained in variants"
	)

	fun unknownParameterEncountered(vararg parameterNameLabels: String) = Failure(
			"UNKNOWN_PARAMETER_ENCOUNTERED",
			"@${parameterNameLabels.toList()}: unknown parameter(s) encountered"
	)
}