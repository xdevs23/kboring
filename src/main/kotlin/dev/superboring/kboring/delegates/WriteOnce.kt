package dev.superboring.kboring.delegates

import kotlin.reflect.KProperty

public class WriteOnce<T> {
    private var holder = holdValue<T>()
    private var value by holder

    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (!holder.hasValue) {
            throw IllegalStateException("Property must be initialized before use")
        }
        return value
    }

    public operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (holder.hasValue) {
            throw RuntimeException("Write-once property already has a value")
        }
        this.value = value
    }

}


