package me.altered.vectors

import kotlin.math.*

/**
 * A class representing a two-dimensional / planar vector.
 */
class Vector2(var x: Double, var y: Double) {
    constructor() : this(0.0, 0.0)
    constructor(a: Number) : this(a.toDouble(), a.toDouble())
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())

    var length
        get() = sqrt(sqrLength)
        set(value) {
            val newVec = normalized * value
            x = newVec.x
            y = newVec.y
        }
    val sqrLength get() = x * x + y * y
    val normalized get() = this / length
    val abs get() = Vector2(abs(x), abs(y))
    val sign get() = Vector2(sign(x), sign(y))
    val ceil get() = Vector2(ceil(x), ceil(y))
    val floor get() = Vector2(floor(x), floor(y))
    val round get() = Vector2(round(x), round(y))
    val perpendicular get() = Vector2(-y, x)
    val vec3 get() = Vector3(x, y, 0.0)
    val vec4 get() = Vector4(x, y, 0.0, 0.0)

    companion object {
        val right get() = Vector2(1.0, 0.0)
        val left get() = Vector2(-1.0, 0.0)
        val up get() = Vector2(0.0, 1.0)
        val down get() = Vector2(0.0, -1.0)
        val one get() = Vector2(1.0, 1.0)
        val zero get() = Vector2(0.0, 0.0)
        val negativeInfinity get() = Vector2(Double.NEGATIVE_INFINITY)
        val positiveInfinity get() = Vector2(Double.POSITIVE_INFINITY)
    }

    operator fun plus(other: Vector2) = Vector2(x + other.x, y + other.y)
    operator fun plus(other: Vector3) = Vector3(x + other.x, y + other.y, other.z)
    operator fun plus(other: Vector4) = Vector4(x + other.x, y + other.y, other.z, other.w)

    operator fun minus(other: Vector2) = Vector2(x - other.x, y - other.y)
    operator fun minus(other: Vector3) = Vector3(x - other.x, y - other.y, -other.z)
    operator fun minus(other: Vector4) = Vector4(x - other.x, y - other.y, -other.z, -other.w)

    operator fun times(a: Number) = Vector2(x * a.toDouble(), y * a.toDouble())
    operator fun times(other: Vector2) = Vector2(x * other.x, y * other.y)
    operator fun times(other: Vector3) = Vector3(x * other.x, y * other.y, 0.0)
    operator fun times(other: Vector4) = Vector4(x * other.x, y * other.y, 0.0, 0.0)
    operator fun times(rotation: Quaternion) = rotation * vec3

    operator fun div(a: Number) = Vector2(x / a.toDouble(), y / a.toDouble())
    operator fun div(other: Vector2) = Vector2(x / other.x, y / other.y)
    operator fun div(other: Vector3) = Vector3(x / other.x, y / other.y, Double.POSITIVE_INFINITY)
    operator fun div(other: Vector4) =
        Vector4(x / other.x, y / other.y, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)

    operator fun rem(a: Number) = Vector2(x % a.toDouble(), y % a.toDouble())
    operator fun rem(other: Vector2) = Vector2(x % other.x, y % other.y)
    operator fun rem(other: Vector3) = Vector3(x % other.x, y % other.y, Double.NaN)
    operator fun rem(other: Vector4) = Vector4(x % other.x, y % other.y, Double.NaN, Double.NaN)

    operator fun compareTo(other: Vector2) = length.compareTo(other.length)
    operator fun compareTo(other: Vector3) = length.compareTo(other.length)
    operator fun compareTo(other: Vector4) = length.compareTo(other.length)

    operator fun unaryMinus() = Vector2(-x, -y)

    /**The distance from this vector to [other].*/
    infix fun distanceTo(other: Vector2) = (other - this).length
    /**The distance from this vector to [other].*/
    infix fun distanceTo(other: Vector3) = (other - this).length
    /**The distance from this vector to [other].*/
    infix fun distanceTo(other: Vector4) = (other - this).length

    /**The normal direction from this vector to [other].*/
    infix fun directionTo(other: Vector2) = (other - this).normalized
    /**The normal direction from this vector to [other].*/
    infix fun directionTo(other: Vector3) = (other - this).normalized
    /**The normal direction from this vector to [other].*/
    infix fun directionTo(other: Vector4) = (other - this).normalized

    /**
     * Dot product.
     * @return The dot product between this vector and [other].
     */
    infix fun dot(other: Vector2) = x * other.x + y * other.y
    /**
     * Dot product.
     * @return The dot product between this vector and [other].
     */
    infix fun dot(other: Vector3) = x * other.x + y * other.y
    /**
     * Dot product.
     * @return The dot product between this vector and [other].
     */
    infix fun dot(other: Vector4) = x * other.x + y * other.y

    /**
     * The angle between two vectors.
     * @return The angle between this vector and [other].
     */
    infix fun angleTo(other: Vector2) = acos(dot(other) / (length * other.length))
    /**
     * The angle between two vectors.
     * @return The angle between this vector and [other].
     */
    infix fun angleTo(other: Vector3) = other angleTo this
    /**
     * The angle between two vectors.
     * @return The angle between this vector and [other].
     */
//    infix fun angleTo(other: Vector4) = other angleTo this

    infix fun signedAngleTo(other: Vector2) = angleTo(other) * sign(cross(other))
    fun signedAngleTo(other: Vector3, axis: Vector3) = other.signedAngleTo(this, axis)

    /**
     * Cross product.
     * @return The cross product between this vector and [other].
     */
    infix fun cross(other: Vector2) = x * other.x - y * other.y
    /**
     * Cross product.
     * @return The cross product between this vector and [other].
     */
    infix fun cross(other: Vector3) = other cross this

    /**The projection of this vector onto [other].*/
    infix fun projectOn(other: Vector2) = other * dot(other) / other.sqrLength
    /**The projection of this vector onto [other].*/
    infix fun projectOn(other: Vector3) = other * dot(other) / other.sqrLength
    /**The projection of this vector onto [other].*/
    infix fun projectOn(other: Vector4) = other * dot(other) / other.sqrLength

    /**Reflects the vector from [normal].*/
    infix fun reflectFrom(normal: Vector2): Vector2 {
        val norm = normal.normalized
        val factor = -2.0 * this dot norm
        return factor * norm + this
    }
    /**Reflects the vector from [normal].*/
    infix fun reflectFrom(normal: Vector3) = vec3 reflectFrom normal
    /**Reflects the vector from [normal].*/
    infix fun reflectFrom(normal: Vector4) = vec4 reflectFrom normal

    /**
     * Rotates the vector by [angle] radians
     * @param angle The angle in **radians**.
     * @return The vector rotated by [angle] radians.
     */
    infix fun rotatedBy(angle: Double): Vector2 {
        val sin = sin(angle)
        val cos = cos(angle)
        return Vector2((cos * x) - (sin * y), (sin * x) + (cos * y))
    }

    override fun toString() = "{ $x; $y }"
    override fun equals(other: Any?) = other is Vector2 && x == other.x && y == other.y
    override fun hashCode() = 31 * x.hashCode() + y.hashCode()
}