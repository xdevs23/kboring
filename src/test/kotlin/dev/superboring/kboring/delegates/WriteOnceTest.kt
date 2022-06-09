package dev.superboring.kboring.delegates

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.platform.commons.annotation.Testable
import kotlin.test.expect

@Testable
internal class WriteOnceTest {

    @Test
    fun testWriteOnce() {
        assertDoesNotThrow {
            var v by WriteOnce<String>()
            v = "only once"
            expect("only once") { v }
        }
        assertThrows {
            val v by WriteOnce<String>()
            v.length
        } as Any
        assertThrows {
            var v by WriteOnce<String>()
            try {
                v = "first"
                v.length
                v = "second"
                v.length
            } catch (e: Exception) {
                expect("first") { v }
                error(e)
            }
        } as Any
    }

}