package dev.superboring.kboring.extensions

import java.net.URLDecoder
import java.util.*

internal infix fun Int.repeat(seq: String): String =
    (0 until this).fold("") { acc, _ -> "$acc$seq" }

public val Iterable<String>.linesAsString: String get() = joinToString("\n")
public operator fun String.times(count: Int): String = count repeat this

public val String.base64: String get() = Base64.getEncoder().encodeToString(toByteArray())
public val String.urlDecoded: String get() = URLDecoder.decode(this, Charsets.UTF_8)
