package io.github.kk_mats.ccvoltiserver.domain.`object`

import io.github.kk_mats.ccvoltiserver.domain.type.*
import io.github.kk_mats.ccvoltiserver.domain.type.limitation.RangeLimitation
import io.github.kk_mats.ccvoltiserver.domain.type.limitation.VariantLimitation

object DetectorInfo {
	val name = Label("CCVolti", "ccvolti")
	val versions = VariantLimitation(Label("version"), Label("1.0"), Label("1.2"), Label("1.0"))

	val rangeLimitations = hashSetOf(
			RangeLimitation(Label("similarity", "sim"), 0.0, 1.0, 0.9),
			RangeLimitation(Label("size", "size"), 0.0, null, 50.0),
			RangeLimitation(Label("sizeb", "sizeb"), 0.0, null, 50.0)
	)

	val variantLimitations = hashSetOf(
			VariantLimitation(Label("charset", "charset"), "UTF-8", "UTF-8"),
			VariantLimitation(Label("language", "lang"), "java", "java", "c", "csharp")
	)
}