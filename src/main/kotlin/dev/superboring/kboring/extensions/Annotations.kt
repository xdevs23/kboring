package dev.superboring.kboring.extensions

import kotlin.reflect.KClass
import kotlin.reflect.KAnnotatedElement
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty

public val KClass<*>.allAnnotations get() = (allSuperclasses + this).flatMap { it.annotations }.toSet()

public inline fun <reified A : Annotation> KClass<*>.hasAnnotation(annotation: KClass<A>) =
    allAnnotations.any { it.annotationClass == annotation }

public inline fun <reified A : Annotation> KClass<*>.hasAnnotation() = hasAnnotation(A::class)

public inline fun <reified A : Annotation> KAnnotatedElement.annotationOrNull(annotation: KClass<A>): A? =
    annotations.find { it.annotationClass == annotation } as? A

public inline fun <reified A : Annotation> KAnnotatedElement.annotation(annotation: KClass<A>) : A =
    annotations.find { it.annotationClass == annotation } as? A
        ?: throw NotImplementedError("Missing annotation ${annotation.qualifiedName} on $this")

public inline fun <reified A : Annotation> KClass<*>.annotationOrNull(annotation: KClass<A>): A? =
    allAnnotations.find { it.annotationClass == annotation } as? A

public inline fun <reified A : Annotation> KClass<*>.annotation(annotation: KClass<A>): A =
    allAnnotations.find { it.annotationClass == annotation } as? A
        ?: throw NotImplementedError("Missing annotation ${annotation.qualifiedName} on class $qualifiedName")

public inline fun <reified A : Annotation> KProperty<*>.annotation(): A =
    annotations.find { it.annotationClass == A::class } as? A
        ?: throw NotImplementedError("Missing annotation ${A::class.qualifiedName} on property $name")

public inline fun <reified A : Annotation> KParameter.annotation(): A =
    annotations.find { it.annotationClass == A::class } as? A
        ?: throw NotImplementedError("Missing annotation ${A::class.qualifiedName} on parameter $name")

public inline fun <reified A : Annotation> Any.hasAnnotation() = this::class.hasAnnotation<A>()
public inline fun <reified A : Annotation> Any.annotation() = this::class.annotation(A::class)
public inline fun <reified A : Annotation> Any.annotationOrNull() = this::class.annotationOrNull(A::class)
public inline fun <reified A : Annotation> KClass<*>.annotation() = annotation(A::class)

