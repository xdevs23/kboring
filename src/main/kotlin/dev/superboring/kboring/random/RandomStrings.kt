package dev.superboring.kboring.random

public object RandomStrings {
    public fun alphanumeric(len: Int): String =
        (0 until len).map { (('a'..'z') + ('A'..'Z') + ('0'..'9')).random() }.joinToString("")

    public fun alphabetic(len: Int): String =
        (0 until len).map { (('a'..'z') + ('A'..'Z')).random() }.joinToString("")
}
