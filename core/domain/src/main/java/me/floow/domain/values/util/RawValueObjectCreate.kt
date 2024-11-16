package me.floow.domain.values.util

@RequiresOptIn(message = "Raw object creation can prove unsatisfying of business behaviours")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR)
annotation class RawValueObjectCreate