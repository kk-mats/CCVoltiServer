package io.github.kk_mats.ccvoltiserver.domain.repository

import io.github.kk_mats.ccvoltiserver.domain.type.Label
import io.github.kk_mats.ccvoltiserver.domain.type.limitation.RangeLimitation
import io.github.kk_mats.ccvoltiserver.domain.type.limitation.VariantLimitation

interface DetectorInfoRepository {
	fun isSupportedVersion(slug: String): Boolean

	fun rangeLimitations(): HashSet<RangeLimitation>
	fun variantLimitations(): HashSet<VariantLimitation<String>>
}