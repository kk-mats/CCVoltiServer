package io.github.kk_mats.ccvoltiserver.infrastructure

import io.github.kk_mats.ccvoltiserver.domain.`object`.DetectorInfo
import io.github.kk_mats.ccvoltiserver.domain.repository.DetectorInfoRepository
import io.github.kk_mats.ccvoltiserver.domain.type.Label
import io.github.kk_mats.ccvoltiserver.domain.type.limitation.RangeLimitation
import io.github.kk_mats.ccvoltiserver.domain.type.limitation.VariantLimitation
import org.springframework.stereotype.Repository

@Repository
class DetectorRepositoryImpl : DetectorInfoRepository {
	override fun isSupportedVersion(slug: String): Boolean {
		return DetectorInfo.versions.validate(Label(slug)).value != null
	}

	override fun rangeLimitations(): HashSet<RangeLimitation> {
		return DetectorInfo.rangeLimitations
	}

	override fun variantLimitations(): HashSet<VariantLimitation<String>> {
		return DetectorInfo.variantLimitations
	}
}