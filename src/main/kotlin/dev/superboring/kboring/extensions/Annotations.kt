package dev.superboring.kboring.extensions

import kotlin.reflect.KClass
import kotlin.reflect.KAnnotatedElement
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty


public inline fun <reified A : Annotation> KAnnotatedElement.annotationOrNull(annotation: KClass<A>): A? =
    annotations.find { it.annotationClass == annotation } as? A

public inline fun <reified A : Annotation> KAnnotatedElement.annotation(annotation: KClass<A>) : A =
    annotations.find { it.annotationClass == annotation } as? A
        ?: throw NotImplementedError("Missing annotation ${annotation.qualifiedName} on $this")

public inline fun <reified A : Annotation> KClass<*>.annotationOrNull(annotation: KClass<A>): A? =
    annotations.find { it.annotationClass == annotation } as? A

public inline fun <reified A : Annotation> KClass<*>.annotation(annotation: KClass<A>): A =
    annotations.find { it.annotationClass == annotation } as? A
        ?: throw NotImplementedError("Missing annotation ${annotation.qualifiedName} on class $qualifiedName")

public inline fun <reified A : Annotation> KProperty<*>.annotation(): A =
    annotations.find { it.annotationClass == A::class } as? A
        ?: throw NotImplementedError("Missing annotation ${A::class.qualifiedName} on property $name")

public inline fun <reified A : Annotation> KParameter.annotation(): A =
    annotations.find { it.annotationClass == A::class } as? A
        ?: throw NotImplementedError("Missing annotation ${A::class.qualifiedName} on parameter $name")

        
