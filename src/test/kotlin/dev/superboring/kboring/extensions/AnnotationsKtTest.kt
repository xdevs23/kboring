package dev.superboring.kboring.extensions

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.platform.commons.annotation.Testable
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@Testable
internal class AnnotationsKtTest {

    private annotation class TestAnnotation
    private annotation class ExampleAnnotation
    private annotation class UnusedAnnotation

    @TestAnnotation
    @ExampleAnnotation
    private class ExampleClass

    @TestAnnotation
    private val annotatedVal = null

    @Suppress("ImplicitNullableNothingType")
    @ExampleAnnotation
    private var annotatedVar = null

    @Suppress("USELESS_IS_CHECK")
    @Test
    fun testAnnotationExtensions() {
        assertNotNull(ExampleClass::class.annotationOrNull(TestAnnotation::class))
        assertNotNull(ExampleClass::class.annotationOrNull(ExampleAnnotation::class))
        assertNull(ExampleClass::class.annotationOrNull(UnusedAnnotation::class))
        assertDoesNotThrow { ExampleClass::class.annotation(TestAnnotation::class) }
        assertDoesNotThrow { ExampleClass::class.annotation(ExampleAnnotation::class) }
        assertTrue(ExampleClass::class.annotation(TestAnnotation::class) is TestAnnotation)
        assertTrue(ExampleClass::class.annotation(ExampleAnnotation::class) is ExampleAnnotation)
        assertThrows { ExampleClass::class.annotation(UnusedAnnotation::class) } as Any

        assertNotNull(::annotatedVal.annotationOrNull(TestAnnotation::class))
        assertNull(::annotatedVal.annotationOrNull(ExampleAnnotation::class))
        assertNull(::annotatedVal.annotationOrNull(UnusedAnnotation::class))

        assertNotNull(::annotatedVar.annotationOrNull(ExampleAnnotation::class))
        assertNull(::annotatedVar.annotationOrNull(TestAnnotation::class))
        assertNull(::annotatedVar.annotationOrNull(UnusedAnnotation::class))
    }

}