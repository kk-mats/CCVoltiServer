package io.github.kk_mats.ccvoltiserver.domain.`object`

import io.github.kk_mats.ccvoltiserver.domain.type.Label
import io.github.kk_mats.ccvoltiserver.domain.type.limitation.*

object DetectorInfo {
	val name = Label("CCVolti", "ccvolti")
	val versions = VariantLimitation(
			Label("version"),
			Label("./ccvolti/1.0/ccvolti.jar", "1.0", "1.0", Int.MAX_VALUE),
			Label("./ccvolti/1.0/ccvolti.jar", "1.0", "1.0", Int.MAX_VALUE)
	)

	val doubleRangeLimitations = hashSetOf(
			RangeLimitation(Label("--sim", "similarity"), 0.0, 1.0, 0.9)
	)

	val intRangeLimitations = hashSetOf(
			RangeLimitation(Label("--size", "size"), 0, null, 50),
			RangeLimitation(Label("--sizeb", "sizeb"), 0, null, 50)
	)

	val regexLimitations = hashSetOf(
			RegexLimitation(Label("-oc", "output csv"), Regex("""[\w-_\.\\//]+"""), "output-csv.csv"),
			RegexLimitation(Label("-ot", "output txt"), Regex("""[\w-_\.\\//]+"""), "output-txt.txt"),
			RegexLimitation(Label("-on", "output notifier"), Regex("""[\w-_\.\\//]+"""), "output-notifier"),
			RegexLimitation(Label("-ocs", "output cloneset"), Regex("""[\w-_\.\\//]+"""), "output-cloneset")
	)

	val variantLimitations = hashSetOf(
			VariantLimitation(Label("--charset", "charset"), "UTF-8", "UTF-8"),
			VariantLimitation(Label("--lang", "language", 1), "java", "java", "c", "csharp")
	)
}