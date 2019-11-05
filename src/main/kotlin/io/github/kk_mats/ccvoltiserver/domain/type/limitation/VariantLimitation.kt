package io.github.kk_mats.ccvoltiserver.domain.type.limitation

import io.github.kk_mats.ccvoltiserver.domain.type.*


class VariantLimitation<T> private constructor(
		label: Label,
		private val default: T?,
		private val variants: HashSet<T>
) : Limitation<T>(label) {
	init {
		if (default != null) {
			require(variants.contains(default)) {
				FailureCode.parameterNotContained(this.label.name, this.default)
			}
		}
	}

	constructor(parameterName: Label, default: T?, vararg variants: T)
			: this(parameterName, default, hashSetOf(*variants))

	override fun validate(value: T?): Failable<Boolean> {
		if (value == null) {
			return when {
				this.default != null -> succeed(true)
				else -> fail(FailureCode.parameterUndefinedWithoutDefault(this.label.name))
			}
		}

		return when {
			this.variants.contains(value) -> succeed(true)
			else -> fail(FailureCode.parameterNotContained(this.label.name, value.toString()))
		}
	}
}
