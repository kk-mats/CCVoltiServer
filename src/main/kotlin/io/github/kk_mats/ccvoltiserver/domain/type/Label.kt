package io.github.kk_mats.ccvoltiserver.domain.type

data class Label(val name: String, val slug: String = name) : Comparable<Label> {
	override fun equals(other: Any?): Boolean {
		return when {
			this === other -> true
			this.javaClass != other?.javaClass -> false
			else -> {
				other as Label
				return this.slug == other.slug
			}
		}
	}

	override fun hashCode(): Int = this.slug.hashCode()

	override fun compareTo(other: Label): Int {
		return this.slug.compareTo(other.slug)
	}
}