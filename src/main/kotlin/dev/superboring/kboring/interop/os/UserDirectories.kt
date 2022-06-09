package dev.superboring.kboring.interop.os

import dev.superboring.kboring.interop.os.common.xdg.XDGUserDirectories
import dev.superboring.kboring.interop.os.windows.WindowsUserDirectories
import java.nio.file.Path
import kotlin.io.path.exists

public interface UserDirectories {
    public val config: Path?
    public val data: Path?
}

public val userDirectories: UserDirectories by lazy {
    when {
        WindowsUserDirectories.config?.exists() ?: false -> WindowsUserDirectories
        XDGUserDirectories.config?.exists() ?: false -> XDGUserDirectories
        else -> throw IllegalStateException("Could not determine user directories. Your system is not compliant.")
    }
}