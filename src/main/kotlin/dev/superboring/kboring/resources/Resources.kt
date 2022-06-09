package dev.superboring.kboring.resources

import java.io.InputStream

@JvmInline
public value class ResourcePath(public val path: String)

public inline infix fun <R> ResourcePath.open(whileOpened: (InputStream) -> R): R =
    stream().use(whileOpened)

public fun ResourcePath.stream(): InputStream = javaClass.getResourceAsStream(path)
    ?: throw IllegalArgumentException("Resource path denoted by $this cannot be streamed")
