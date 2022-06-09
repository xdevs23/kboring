package dev.superboring.kboring.geometry

public data class Rectangle(var width: DP, var height: DP) {

    public fun copy(): Rectangle {
        return Rectangle(width, height)
    }

    public operator fun unaryPlus(): Rectangle = Rectangle(+width, +height)
    public operator fun unaryMinus(): Rectangle = Rectangle(-width, -height)

    public operator fun plus(other: Rectangle): Rectangle = Rectangle(width + other.width, height + other.height)
    public operator fun minus(other: Rectangle): Rectangle = Rectangle(width - other.width, height - other.height)
    public operator fun times(other: Rectangle): Rectangle = Rectangle(width * other.width, height * other.height)
    public operator fun div(other: Rectangle): Rectangle = Rectangle(width / other.width, height / other.height)

    public operator fun plusAssign(other: Rectangle) {
        width = width.plus(other.width)
        height = height.plus(other.height)
    }

    public operator fun minusAssign(other: Rectangle) {
        width = width.minus(other.width)
        height = height.minus(other.height)
    }

    public operator fun timesAssign(other: Rectangle) {
        width = width.times(other.width)
        height = height.times(other.height)
    }

    public operator fun divAssign(other: Rectangle) {
        width = width.div(other.width)
        height = height.div(other.height)
    }

    public fun center(width: Boolean, height: Boolean): Point {
        return Point(if (width) this.width / 2.dp else 0.dp, if (height) this.height / 2.dp else 0.dp)
    }

    val center: Point
        get() = center(width = true, height = true)

    override operator fun equals(other: Any?): Boolean {
        if (other !is Rectangle) {
            return false
        }
        return width == other.width && height == other.height
    }
}