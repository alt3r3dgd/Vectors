package me.altered.vectors

import kotlin.math.*

/**
 * A class representing a four-dimensional vector.
 */
class Vector4(var x: Double, var y: Double, var z: Double, var w: Double) {
    constructor() : this(0.0, 0.0, 0.0, 0.0)
    constructor(a: Number) : this(a.toDouble(), a.toDouble(), a.toDouble(), a.toDouble())
    constructor(x: Number, y: Number, z: Number, w: Number)
            : this(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())

    var length
        get() = sqrt(sqrLength)
        set(value) {
            val newVec = normalized * value
            x = newVec.x
            y = newVec.y
            z = newVec.z
            w = newVec.w
        }
    val sqrLength get() = x * x + y * y + z * z + w * w
    val normalized get() = this / length
    val abs get() = Vector4(abs(x), abs(y), abs(z), abs(w))
    val sign get() = Vector4(sign(x), sign(y), sign(z), sign(w))
    val ceil get() = Vector4(ceil(x), ceil(y), ceil(z), ceil(w))
    val floor get() = Vector4(floor(x), floor(y), floor(z), floor(w))
    val round get() = Vector4(round(x), round(y), round(z), round(w))
    val vec2 get() = Vector2(x, y)
    val vec3 get() = Vector3(x, y, z)

    companion object {
        val one get() = Vector4(1.0, 1.0, 1.0, 1.0)
        val zero get() = Vector4(0.0, 0.0, 0.0, 0.0)
        val right get() = Vector4(1.0, 0.0, 0.0, 0.0)
        val left get() = Vector4(-1.0, 0.0, 0.0, 0.0)
        val up get() = Vector4(0.0, 1.0, 0.0, 0.0)
        val down get() = Vector4(0.0, -1.0, 0.0, 0.0)
        val forward get() = Vector4(0.0, 0.0, 1.0, 0.0)
        val backward get() = Vector4(0.0, 0.0, -1.0, 0.0)
        val negativeInfinity get() = Vector4(Double.NEGATIVE_INFINITY)
        val positiveInfinity get() = Vector4(Double.POSITIVE_INFINITY)
    }

    operator fun plus(other: Vector4) = Vector4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun plus(other: Vector3) = Vector4(x + other.x, y + other.y, z + other.z, w)
    operator fun plus(other: Vector2) = Vector4(x + other.x, y + other.y, z, w)

    operator fun minus(other: Vector4) = Vector4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun minus(other: Vector3) = Vector4(x - other.x, y - other.y, z - other.z, w)
    operator fun minus(other: Vector2) = Vector4(x - other.x, y - other.y, z, w)

    operator fun times(a: Number) = Vector4(x * a.toDouble(), y * a.toDouble(), z * a.toDouble(), w * a.toDouble())
    operator fun times(other: Vector4) = Vector4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun times(other: Vector3) = Vector4(x * other.x, y * other.y, z * other.z, 0.0)
    operator fun times(other: Vector2) = Vector4(x * other.x, y * other.y, 0.0, 0.0)

    operator fun div(a: Number) = Vector4(x / a.toDouble(), y / a.toDouble(), z / a.toDouble(), w / a.toDouble())
    operator fun div(other: Vector4) = Vector4(x / other.x, y / other.y, z / other.z, w / other.w)
    operator fun div(other: Vector3) = Vector4(x / other.x, y / other.y, z / other.z, Double.POSITIVE_INFINITY)
    operator fun div(other: Vector2) =
        Vector4(x / other.x, y / other.y, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)

    operator fun rem(a: Number) = Vector4(x % a.toDouble(), y % a.toDouble(), z % a.toDouble(), w % a.toDouble())
    operator fun rem(other: Vector4) = Vector4(x % other.x, y % other.y, z % other.z, w % other.w)
    operator fun rem(other: Vector3) = Vector4(x % other.x, y % other.y, z % other.z, Double.NaN)
    operator fun rem(other: Vector2) = Vector4(x % other.x, y % other.y, Double.NaN, Double.NaN)

    operator fun compareTo(other: Vector4) = length.compareTo(other.length)

    operator fun unaryMinus() = Vector4(-x, -y, -z, -w)

    /**The distance from this vector to [other].*/
    infix fun distanceTo(other: Vector4) = (other - this).length
    /**The distance from this vector to [other].*/
    infix fun distanceTo(other: Vector3) = (other - this).length
    /**The distance from this vector to [other].*/
    infix fun distanceTo(other: Vector2) = (other - this).length

    /**The normal direction from this vector to [other].*/
    infix fun directionTo(other: Vector2) = (other - this).normalized
    /**The normal direction from this vector to [other].*/
    infix fun directionTo(other: Vector3) = (other - this).normalized
    /**The normal direction from this vector to [other].*/
    infix fun directionTo(other: Vector4) = (other - this).normalized

    infix fun dot(other: Vector4) = x * other.x + y * other.y + z * other.z + w * other.w
    infix fun dot(other: Vector3) = x * other.x + y * other.y + z * other.z
    infix fun dot(other: Vector2) = x * other.x + y * other.y

    /**The projection of this vector onto [other].*/
    infix fun projectOn(other: Vector2) = other * dot(other) / other.sqrLength
    /**The projection of this vector onto [other].*/
    infix fun projectOn(other: Vector3) = other * dot(other) / other.sqrLength
    /**The projection of this vector onto [other].*/
    infix fun projectOn(other: Vector4) = other * dot(other) / other.sqrLength

    /**Reflects the vector from [normal].*/
    infix fun reflectFrom(normal: Vector4): Vector4 {
        val norm = normal.normalized
        val factor = -2.0 * this dot norm
        return factor * norm + this
    }
    /**Reflects the vector from [normal].*/
    infix fun reflectFrom(normal: Vector3) = reflectFrom(normal.vec4)
    /**Reflects the vector from [normal].*/
    infix fun reflectFrom(normal: Vector2) = reflectFrom(normal.vec4)

    override fun toString() = "{ $x; $y; $z; $w }"
    override fun equals(other: Any?) = other is Vector4 && x == other.x && y == other.y && z == other.z && w == other.w
    override fun hashCode() = 31 * (31 * (31 * x.hashCode() + y.hashCode()) + z.hashCode()) + w.hashCode()
}