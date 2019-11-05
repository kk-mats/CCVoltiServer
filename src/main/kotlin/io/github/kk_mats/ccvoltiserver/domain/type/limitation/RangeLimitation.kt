package io.github.kk_mats.ccvoltiserver.domain.type.limitation

import io.github.kk_mats.ccvoltiserver.domain.type.*


class RangeLimitation(
		label: Label, private val min: Double?, private val max: Double?, private val default: Double?
) : Limitation<Double>(label) {
	init {
		if (this.default != null) {
			if (this.min != null) {
				require(this.min <= this.default) {
					FailureCode.parameterDefaultOutOfRange(this.label.name, this.default, this.min, this.max)
				}
			}
			if (this.max != null) {
				require(this.default <= this.max) {
					FailureCode.parameterDefaultOutOfRange(this.label.name, this.default, this.min, this.max)
				}
			}
		} else if (this.min != null && this.max != null) {
			require(this.min < this.max) {
				FailureCode.invalidParameterRange(this.label.name, this.min, this.max)
			}
		}
	}

	override fun validate(value: Double?): Failable<Boolean> {
		if (value == null) {
			return when {
				this.default != null -> succeed(true)
				else -> fail(FailureCode.parameterUndefinedWithoutDefault(this.label.name))
			}
		}

		if (this.min != null && value < this.min) {
			return fail(FailureCode.parameterOutOfRange(this.label.name, value, this.min, this.max))
		}

		if (this.max != null && this.max < value) {
			return fail(FailureCode.parameterOutOfRange(this.label.name, value, this.min, this.max))
		}

		return succeed(true)
	}
}
