package dev.superboring.kboring.extensions.java.nio.file

import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.pathString

public operator fun Path.plus(p: Path): Path = Path(pathString, p.pathString)
public operator fun Path.div(p: Path): Path = plus(p)
public operator fun Path.plus(p: String): Path = Path(pathString, p)
public operator fun Path.div(p: String): Path = plus(p)
