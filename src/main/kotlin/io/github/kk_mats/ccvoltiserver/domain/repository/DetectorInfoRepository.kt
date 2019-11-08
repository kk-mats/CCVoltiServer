package io.github.kk_mats.ccvoltiserver.domain.repository

import io.github.kk_mats.ccvoltiserver.domain.type.Failable
import io.github.kk_mats.ccvoltiserver.domain.type.Label
import io.github.kk_mats.ccvoltiserver.domain.type.limitation.*

interface DetectorInfoRepository {
	fun isSupportedVersion(slug: String): Boolean
	fun version(): VariantLimitation<Label>

	fun intRangeLimitations(): HashSet<RangeLimitation<Int>>
	fun doubleRangeLimitations(): HashSet<RangeLimitation<Double>>
	fun regexLimitations(): HashSet<RegexLimitation>
	fun variantLimitations(): HashSet<VariantLimitation<String>>
}