package dev.superboring.kboring.concurrency

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.platform.commons.annotation.Testable

@Testable
internal class CoroutineScopeKtTest {

    @Test
    fun launchIO() {
        runBlocking {
            launchIO { println("This ran in IO coroutine ($coroutineContext)") }
        }
    }

}