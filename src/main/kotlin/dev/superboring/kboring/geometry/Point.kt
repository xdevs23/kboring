package dev.superboring.kboring.geometry

public data class Point(var x: DP = 0.dp, var y: DP = 0.dp) {

    public operator fun unaryPlus(): Point = Point(+x, +y)
    public operator fun unaryMinus(): Point = Point(-x, -y)
    public operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    public operator fun minus(other: Point): Point = Point(x - other.x, y - other.y)
    public operator fun times(other: Point): Point = Point(x * other.x, y * other.y)
    public operator fun div(other: Point): Point = Point(x / other.x, y / other.y)

    public operator fun plusAssign(other: Point) {
        x = x.plus(other.x)
        y = y.plus(other.y)
    }

    public operator fun minusAssign(other: Point) {
        x = x.minus(other.x)
        y = y.minus(other.y)
    }

    public operator fun timesAssign(other: Point) {
        x = x.times(other.x)
        y = y.times(other.y)
    }

    public operator fun divAssign(other: Point) {
        x = x.div(other.x)
        y = y.div(other.y)
    }

    public fun add(both: DP): Point = apply {
        x = x.plus(both)
        y = y.plus(both)
    }

    public fun add(point: Point): Point = apply { this += point }
    public fun subtract(point: Point): Point = apply { this -= point }
    public fun multiply(point: Point): Point = apply { this *= point }
    public fun divide(point: Point): Point = apply { this /= point }

    public fun clamp(
        minX: DP,
        minY: DP,
        maxX: DP,
        maxY: DP
    ): Point = apply {
        x = x.coerceIn(minX, maxX.coerceAtLeast(minX))
        y = y.coerceIn(minY, maxY.coerceAtLeast(minY))
    }

    override operator fun equals(other: Any?): Boolean {
        if (other !is Point) {
            return false
        }
        return x == other.x && y == other.y
    }

    public fun copy(): Point = Point(x, y)

    override fun toString(): String {
        return "{$x,$y}"
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }

    public companion object {
        public val zero: Point get() = Point()
    }

}