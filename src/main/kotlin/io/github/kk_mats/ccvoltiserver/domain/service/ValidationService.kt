package io.github.kk_mats.ccvoltiserver.domain.service

import io.github.kk_mats.ccvoltiserver.domain.repository.DetectorInfoRepository
import io.github.kk_mats.ccvoltiserver.domain.type.Failable
import io.github.kk_mats.ccvoltiserver.domain.type.FailureCode
import io.github.kk_mats.ccvoltiserver.domain.type.fail
import io.github.kk_mats.ccvoltiserver.domain.type.query.DetectionQuery
import io.github.kk_mats.ccvoltiserver.domain.type.succeed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ValidationService {

	@Autowired
	lateinit var detectorInfoRepository: DetectorInfoRepository

	fun validate(query: DetectionQuery): Failable<Boolean> {

		val parameters = query.parameters
		var r: Failable<Boolean>

		for (limitation in this.detectorInfoRepository.rangeLimitations()) {
			r = when (val value = parameters.remove(limitation.label.slug)) {
				null -> limitation.validate(null)
				else -> when (val doubleValue = value.toDoubleOrNull()) {
					null -> fail(FailureCode.invalidRangeParameterType(limitation.label.name, value))
					else -> limitation.validate(doubleValue)
				}
			}

			if (r.error != null) {
				return r
			}
		}

		for (limitation in this.detectorInfoRepository.variantLimitations()) {
			r = limitation.validate(parameters.remove(limitation.label.slug))

			if (r.error != null) {
				return r
			}
		}

		if (parameters.isNotEmpty()) {
			return fail(FailureCode.unknownParameterEncountered(*parameters.keys.toTypedArray()))
		}

		return succeed(true)
	}
}