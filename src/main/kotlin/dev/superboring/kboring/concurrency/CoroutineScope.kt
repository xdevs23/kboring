package dev.superboring.kboring.concurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

public fun CoroutineScope.launchIO(block: suspend CoroutineScope.() -> Unit): Job =
    launch(Dispatchers.IO, block = block)

public fun CoroutineScope.launchMain(block: suspend CoroutineScope.() -> Unit): Job =
    launch(Dispatchers.Main, block = block)
