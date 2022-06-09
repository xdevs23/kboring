package dev.superboring.kboring.delegates

import kotlin.reflect.KProperty

public fun <T> holdValue(): ValueHolder<T> = ValueHolder<T>()

public open class ValueHolder<T> {
    private var value: T? = null
    public var hasValue: Boolean = false
        private set

    public operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
        hasValue = true
    }

    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return this.value!!
    }
}