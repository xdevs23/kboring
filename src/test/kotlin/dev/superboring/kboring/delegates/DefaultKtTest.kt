package dev.superboring.kboring.delegates

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.platform.commons.annotation.Testable
import kotlin.test.expect
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@Testable
internal class DefaultKtTest {

    private fun superExpensiveCalculation() = 100.apply { Thread.sleep(1000) }

    @OptIn(ExperimentalTime::class)
    @Test
    fun testDefault() {
        assertDoesNotThrow {
            val measured = measureTimedValue {
                val defVal by default { superExpensiveCalculation() }
                defVal
            }
            expect(100) { measured.value }
            assertTrue(measured.duration.inWholeMilliseconds >= 1000)
        }
        assertDoesNotThrow {
            val measured = measureTimedValue {
                var v by default { superExpensiveCalculation() }
                v = 1
                v
            }
            expect(1) { measured.value }
            assertTrue(measured.duration.inWholeMilliseconds < 1000)
        }
        assertDoesNotThrow {
            val measured = measureTimedValue {
                var v by default { 1 }
                v = superExpensiveCalculation()
                v
            }
            expect(100) { measured.value }
            assertTrue(measured.duration.inWholeMilliseconds >= 1000)
        }
        assertDoesNotThrow {
            val measured = measureTimedValue {
                var v by default { superExpensiveCalculation() + 10 }
                v = superExpensiveCalculation()
                v
            }
            expect(100) { measured.value }
            assertTrue(measured.duration.inWholeMilliseconds in 1000..2000)
        }
        assertDoesNotThrow {
            val measured = measureTimedValue {
                val defVal by default { superExpensiveCalculation() + 10 }
                defVal
            }
            expect(110) { measured.value }
            assertTrue(measured.duration.inWholeMilliseconds >= 1000)
        }
        assertDoesNotThrow {
            val measured = measureTimedValue {
                var v by default { superExpensiveCalculation() + 10 }
                v = 120
                v
            }
            expect(120) { measured.value }
            assertTrue(measured.duration.inWholeMilliseconds < 1000)
        }
    }

}