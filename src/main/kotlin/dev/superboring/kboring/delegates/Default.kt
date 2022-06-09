package dev.superboring.kboring.delegates

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.reflect.KProperty

/**
 * The default delegator sets the value to the return value
 * of initializer if the value has never been set before there
 * is an attempt to get the value. If the value gets set before
 * there is an attempt to get the value, the set value will be
 * returned instead.
 *
 * This allows you to keep your memory footprint low for large
 * default objects as the initialization is deferred to when the
 * value is actually fetched, except if the value has been set,
 * then the set value will be returned instead of the default which
 * means the default will never be initialized, and you save the
 * initialization cost of the default.
 */
public fun <T> default(initializer: () -> T): Default<T> = Default(initializer)

public class Default<T>(private val initializer: () -> T) {
    private var holder = holdValue<T>()
    private var value by holder
    private val mutex = Mutex()

    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
        runBlocking {
            mutex.withLock {
                if (!holder.hasValue) {
                    value = initializer()
                }
                return@runBlocking value
            }
        }

    public operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        runBlocking {
            mutex.withLock {
                this@Default.value = value
            }
        }
    }
}
