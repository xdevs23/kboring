package dev.superboring.kboring.concurrency

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.platform.commons.annotation.Testable
import kotlin.test.expect

@Testable
internal class TimerTest {

    @Test
    fun testTimer() = runBlocking {
        val secondsWait = 3
        // When the timer starts, the action runs immediately and is then scheduled to run periodically
        val expectedRunCount = secondsWait + 1
        var count = 0
        Timer(1000) {
            println(++count)
        }
        delay(secondsWait * 1000 + 200L)
        expect(expectedRunCount) { count }
    }

}