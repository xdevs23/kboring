package kboring.extensions

internal infix fun Int.repeat(seq: String): String =
    (0 until this).fold("") { acc, _ -> "$acc$seq" }

public val Iterable<String>.linesAsString: String get() = joinToString("\n")
public operator fun String.times(count: Int): String = count repeat this

