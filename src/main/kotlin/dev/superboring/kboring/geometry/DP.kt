package dev.superboring.kboring.geometry

import kotlin.math.abs
import kotlin.math.ceil

/**
 * Density-based Pixels (automatic scaling)
 */
public class DP(public val dp: Double = 0.0, public val scale: Double = 1.0) : Comparable<DP>, Number() {

    public companion object {
        public val zero: DP = DP()
        public val max: DP = Double.MAX_VALUE.dp
        public val min: DP = Double.MIN_VALUE.dp
    }

    public inline val px: Double
        get() = dp * scale
    public inline val absInt: Int
        get() = ceil(px).toInt()
    public inline val absFloat: Float
        get() = px.toFloat()


    private inline val dpInt: Int
        get() = ceil(dp).toInt()
    private inline val dpFloat: Float
        get() = dp.toFloat()

    public operator fun plus(other: DP): DP = DP(dp + other.dp)
    public operator fun minus(other: DP): DP = DP(dp - other.dp)
    public operator fun times(other: DP): DP = DP(dp * other.dp)
    public operator fun div(other: DP): DP = DP(dp / other.dp)
    public operator fun rem(other: DP): DP = DP(dp % other.dp)
    public operator fun plus(other: Double): DP = DP(dp + other)
    public operator fun minus(other: Double): DP = DP(dp - other)
    public operator fun times(other: Double): DP = DP(dp * other)
    public operator fun div(other: Double): DP = DP(dp / other)
    public operator fun rem(other: Double): DP = DP(dp % other)

    public operator fun unaryPlus(): DP = DP(+dp)
    public operator fun unaryMinus(): DP = DP(-dp)

    override operator fun compareTo(other: DP): Int = dp.compareTo(other.dp)

    override fun equals(other: Any?): Boolean = other is DP && dp == other.dp
    override fun hashCode(): Int = dp.toFloat().toRawBits()

    override fun toByte(): Byte = dpInt.toByte()
    override fun toChar(): Char = dpInt.toChar()
    override fun toDouble(): Double = dp
    override fun toFloat(): Float = dpFloat
    override fun toInt(): Int = dpInt
    override fun toLong(): Long = dp.toLong()
    override fun toShort(): Short = dpInt.toShort()

    public fun copy(): DP = dp.dp

    override fun toString(): String {
        return "DP(dp=$dp, abs=$px)"
    }
}

public inline val Int.dp: DP get() =
    when (this) {
        0 -> DP.zero
        else -> DP(this.toDouble())
    }

public inline val Double.dp: DP get() =
    when (this) {
        0.0 -> DP.zero
        else -> DP(this)
    }

public inline val Float.dp: DP get() =
    when (this) {
        0f -> DP.zero
        else -> DP(this.toDouble())
    }

public inline fun <T> Iterable<T>.sumOf(selector: (T) -> DP): DP {
    var sum = DP.zero
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

public fun abs(dp: DP): DP {
    return DP(abs(dp.dp))
}
