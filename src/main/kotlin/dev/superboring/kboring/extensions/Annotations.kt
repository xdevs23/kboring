package dev.superboring.kboring.extensions

import kotlin.reflect.KClass
import kotlin.reflect.KAnnotatedElement

public inline fun <reified A : Annotation> KAnnotatedElement.annotationOrNull(annotation: KClass<A>): A? =
    annotations.find { it.annotationClass == annotation } as? A

public inline fun <reified A : Annotation> KAnnotatedElement.annotation(annotation: KClass<A>) : A =
    annotations.find { it.annotationClass == annotation } as? A
        ?: throw NotImplementedError("Missing annotation ${annotation.qualifiedName} on $this")
