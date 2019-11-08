package io.github.kk_mats.ccvoltiserver.domain.type.limitation

import io.github.kk_mats.ccvoltiserver.domain.type.*

abstract class Limitation<T : Any>(val label: Label, val required: Boolean = false) {
	abstract fun validate(value: T?): Failable<T>
	abstract override fun toString(): String

	final override fun hashCode(): Int = this.label.slug.hashCode()
}
