package kboring.extensions

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.expect

internal class StringsKtTest {

    @Test
    fun getLinesAsString() {
        expect("1\n2\n3") { listOf("1", "2", "3").linesAsString }
        expect("1") { listOf("1").linesAsString }
        expect("1\ntrue\ntest") {
            listOf(1, true, "test").map { "$it" }.linesAsString
        }
    }

    @Test
    fun times() {
        expect("1111") { "1" * 4 }
        expect("testtesttest") { "test" * 3 }
        expect("foo") { "foo" * 1 }
        expect("bar") { "bar" * 1 }
        expect("blablablablabla") { "bla" * 5 }
    }

}