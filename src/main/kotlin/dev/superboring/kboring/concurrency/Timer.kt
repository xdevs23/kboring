package dev.superboring.kboring.concurrency

import kotlin.concurrent.fixedRateTimer

public class Timer(period: Long, private var action: () -> Unit = {}) {
    private val timer: java.util.Timer

    init {
        timer = fixedRateTimer(
            daemon = true,
            period = period,
            action = {
                action()
            }
        )
    }

    public fun stop() {
        timer.cancel()
    }

}