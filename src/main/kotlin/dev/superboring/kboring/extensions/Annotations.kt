package dev.superboring.kboring.extensions

import kotlin.reflect.KClass
import kotlin.reflect.KAnnotatedElement
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty
import kotlin.reflect.full.superclasses

public val KClass<*>.allAnnotations: Set<Annotation> get() =
    (annotations + superclasses.flatMap { it.allAnnotations }).toSet()

public inline fun <reified A : Annotation> KClass<*>.hasAnnotation(annotation: KClass<A>): Boolean =
    allAnnotations.any { it.annotationClass == annotation }

public inline fun <reified A : Annotation> KClass<*>.hasAnnotation(): Boolean = hasAnnotation(A::class)

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

public inline fun <reified A : Annotation> Any.hasAnnotation(): Boolean = this::class.hasAnnotation<A>()
public inline fun <reified A : Annotation> Any.annotation(): Annotation = this::class.annotation(A::class)
public inline fun <reified A : Annotation> Any.annotationOrNull(): Annotation? = this::class.annotationOrNull(A::class)
public inline fun <reified A : Annotation> KClass<*>.annotation(): Annotation = annotation(A::class)

