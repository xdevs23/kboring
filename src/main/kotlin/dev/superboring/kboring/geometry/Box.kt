package dev.superboring.kboring.geometry

public class Box(
    public var pos: Point = Point.zero,
    public var size: Rectangle = Rectangle(0.dp, 0.dp),
    public var depth: Double = 0.0
) {

    public var minX: DP by pos::x
    public var minY: DP by pos::y
    public inline var maxX: DP
        get() = minX + size.width
        set(value) {
            size.width = value - minX
        }

    public inline var maxY: DP
        get() = minY + size.height
        set(value) {
            size.height = value - minY
        }

    public fun coerceIn(min: Box, max: Box): Box {
        if (!intersectsWith(Box(min.pos).apply{ maxX = max.maxX; maxY = max.maxY})) {
            return Box(Point.zero, Rectangle(0.dp, 0.dp), 0.0)
        }
        val box = coerceAtLeast(min)
        box.maxX = maxX.coerceAtMost(max.maxX)
        box.maxY = maxY.coerceAtMost(max.maxY)
        return box
    }

    public fun coerceAtLeast(min: Box): Box = Box(
        Point(pos.x.coerceAtLeast(min.pos.x), pos.y.coerceAtLeast(min.pos.y)),
        depth = depth
    )

    public fun coerceAtMost(max: Box): Box {
        val box = Box(pos, depth = depth)
        box.maxX = maxX.coerceAtMost(max.maxX)
        box.maxY = maxY.coerceAtMost(max.maxY)
        return box
    }

    public infix fun intersectsWith(other: Box): Boolean {
        return other.minX <= maxX && other.minY <= maxY &&
                other.maxX >= minX && other.maxY >= minY
    }

}