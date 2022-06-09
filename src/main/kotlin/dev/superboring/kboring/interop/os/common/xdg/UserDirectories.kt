package dev.superboring.kboring.interop.os.common.xdg

import dev.superboring.kboring.interop.os.UserDirectories
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

public object XDGUserDirectories : UserDirectories {
    override val config: Path? = System.getenv("XDG_CONFIG_HOME").orEmpty()
        .ifEmpty {
            System.getenv("HOME").orEmpty().ifEmpty { null }?.let {
                Path(it, ".config").absolutePathString()
            }
        }?.let { Path(it) }
    override val data: Path? = System.getenv("XDG_CONFIG_DATA").orEmpty()
        .ifEmpty {
            System.getenv("HOME").orEmpty().ifEmpty { null }?.let {
                Path(it, ".local", "share").absolutePathString()
            }
        }?.let { Path(it) }
}