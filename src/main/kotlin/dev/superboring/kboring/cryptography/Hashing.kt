package dev.superboring.kboring.cryptography

import java.security.MessageDigest

internal fun hashString(input: String, algorithm: String): String {
    return MessageDigest
        .getInstance(algorithm)
        .digest(input.toByteArray())
        .fold("") { str, it -> str + "%02x".format(it) }
}

public val String.sha256: String get() = hashString(this, "SHA-256")
