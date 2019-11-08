package io.github.kk_mats.ccvoltiserver.infrastructure

import io.github.kk_mats.ccvoltiserver.domain.`object`.DetectorInfo
import io.github.kk_mats.ccvoltiserver.domain.repository.DetectorInfoRepository
import io.github.kk_mats.ccvoltiserver.domain.type.Label
import io.github.kk_mats.ccvoltiserver.domain.type.limitation.*
import org.springframework.stereotype.Repository

@Repository
class DetectorRepositoryImpl : DetectorInfoRepository {
	override fun isSupportedVersion(slug: String): Boolean {
		return DetectorInfo.versions.validate(Label(slug)).value != null
	}

	override fun version(): VariantLimitation<Label> {
		return DetectorInfo.versions
	}

	override fun intRangeLimitations(): HashSet<RangeLimitation<Int>> {
		return DetectorInfo.intRangeLimitations
	}

	override fun doubleRangeLimitations(): HashSet<RangeLimitation<Double>> {
		return DetectorInfo.doubleRangeLimitations
	}

	override fun regexLimitations(): HashSet<RegexLimitation> {
		return DetectorInfo.regexLimitations
	}

	override fun variantLimitations(): HashSet<VariantLimitation<String>> {
		return DetectorInfo.variantLimitations
	}
}