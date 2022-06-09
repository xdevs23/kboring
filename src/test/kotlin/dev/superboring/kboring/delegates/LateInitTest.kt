package dev.superboring.kboring.delegates

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.platform.commons.annotation.Testable
import kotlin.test.expect

@Testable
internal class LateInitTest {

    @Test
    fun testLateInit() {
        assertThrows {
            val v by LateInit<String>()
            v.length
        } as Any
        assertDoesNotThrow {
            var v by LateInit<String>()
            v = "works"
            expect("works") { v }
        }
    }

}