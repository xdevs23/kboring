package dev.superboring.kboring.interop.os.windows

import dev.superboring.kboring.interop.os.UserDirectories
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

public object WindowsUserDirectories : UserDirectories {
    private val userProfile = System.getenv("UserProfile") ?: ""
    override val data: Path? =
        (System.getenv("APPDATA") ?: "").ifBlank {
            if (userProfile.isBlank()) {
                return@ifBlank ""
            }
            Path(userProfile, "AppData", "Roaming").absolutePathString()
        }.ifBlank { null }?.let { Path(it) }
    override val config: Path? = data
}