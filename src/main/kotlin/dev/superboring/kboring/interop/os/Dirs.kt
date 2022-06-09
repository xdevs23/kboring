package dev.superboring.kboring.interop.os

public val currentTempDirectory: String by lazy { System.getProperty("java.io.tmpdir") }
