package me.altered.vectors

import kotlin.math.*

/**
 * A class representing a three-dimensional / spatial vector.
 */
class Vector3(var x: Double, var y: Double, var z: Double) {
    constructor() : this(0.0, 0.0, 0.0)
    constructor(a: Number) : this(a.toDouble(), a.toDouble(), a.toDouble())
    constructor(x: Number, y: Number, z: Number) : this(x.toDouble(), y.toDouble(), z.toDouble())

    var length
        get() = sqrt(sqrLength)
        set(value) {
            val newVec = normalized * value
            x = newVec.x
            y = newVec.y
            z = newVec.z
        }
    val sqrLength get() = x * x + y * y + z * z
    val normalized get() = this / length
    val abs get() = Vector3(abs(x), abs(y), abs(z))
    val sign get() = Vector3(sign(x), sign(y), sign(z))
    val ceil get() = Vector3(ceil(x), ceil(y), ceil(z))
    val floor get() = Vector3(floor(x), floor(y), floor(z))
    val round get() = Vector3(round(x), round(y), round(z))
    val vec2 get() = Vector2(x, y)
    val vec4 get() = Vector4(x, y, z, 0.0)

    companion object {
        val one get() = Vector3(1.0, 1.0, 1.0)
        val zero get() = Vector3(0.0, 0.0, 0.0)
        val right get() = Vector3(1.0, 0.0, 0.0)
        val left get() = Vector3(-1.0, 0.0, 0.0)
        val up get() = Vector3(0.0, 1.0, 0.0)
        val down get() = Vector3(0.0, -1.0, 0.0)
        val forward get() = Vector3(0.0, 0.0, 1.0)
        val backward get() = Vector3(0.0, 0.0, -1.0)
        val negativeInfinity get() = Vector3(Double.NEGATIVE_INFINITY)
        val positiveInfinity get() = Vector3(Double.POSITIVE_INFINITY)
    }

    operator fun plus(other: Vector2) = Vector3(x + other.x, y + other.y, z)
    operator fun plus(other: Vector3) = Vector3(x + other.x, y + other.y, z + other.z)
    operator fun plus(other: Vector4) = Vector4(x + other.x, y + other.y, z + other.z, other.w)

    operator fun minus(other: Vector2) = Vector3(x - other.x, y - other.y, z)
    operator fun minus(other: Vector3) = Vector3(x - other.x, y - other.y, z - other.z)
    operator fun minus(other: Vector4) = Vector4(x - other.x, y - other.y, z - other.z, -other.w)

    operator fun times(a: Number) = Vector3(x * a.toDouble(), y * a.toDouble(), z * a.toDouble())
    operator fun times(other: Vector2) = Vector3(x * other.x, y * other.y, 0.0)
    operator fun times(other: Vector3) = Vector3(x * other.x, y * other.y, z * other.z)
    operator fun times(other: Vector4) = Vector4(x * other.x, y * other.y, z * other.z, 0.0)
    operator fun times(rotation: Quaternion) = rotation * this

    operator fun div(a: Number) = Vector3(x / a.toDouble(), y / a.toDouble(), z / a.toDouble())
    operator fun div(other: Vector2) = Vector3(x / other.x, y / other.y, Double.POSITIVE_INFINITY)
    operator fun div(other: Vector3) = Vector3(x / other.x, y / other.y, z / other.z)
    operator fun div(other: Vector4) = Vector4(x / other.x, y / other.y, z / other.z, Double.POSITIVE_INFINITY)

    operator fun rem(a: Number) = Vector3(x % a.toDouble(), y % a.toDouble(), z % a.toDouble())
    operator fun rem(other: Vector2) = Vector3(x % other.x, y % other.y, Double.NaN)
    operator fun rem(other: Vector3) = Vector3(x % other.x, y % other.y, z % other.z)
    operator fun rem(other: Vector4) = Vector4(x % other.x, y % other.y, z % other.z, Double.NaN)

    operator fun compareTo(other: Vector2) = length.compareTo(other.length)
    operator fun compareTo(other: Vector3) = length.compareTo(other.length)
    operator fun compareTo(other: Vector4) = length.compareTo(other.length)

    operator fun unaryMinus() = Vector3(-x, -y, -z)

    /**The distance from this vector to [other].*/
    infix fun distanceTo(other: Vector3) = (other - this).length
    /**The distance from this vector to [other].*/
    infix fun distanceTo(other: Vector2) = (other - this).length
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
    infix fun dot(other: Vector3) = x * other.x + y * other.y + z * other.z
    /**
     * Dot product.
     * @return The dot product between this vector and [other].
     */
    infix fun dot(other: Vector4) = x * other.x + y * other.y + z * other.z

    /**
     * The angle between two vectors.
     * @return The angle between this vector and [other].
     */
    infix fun angleTo(other: Vector2) = atan2(cross(other).length, dot(other))
    /**
     * The angle between two vectors.
     * @return The angle between this vector and [other].
     */
    infix fun angleTo(other: Vector3) = atan2(cross(other).length, dot(other))
    /**
     * The angle between two vectors.
     * @return The angle between this vector and [other].
     */
//    infix fun angleTo(other: Vector4) = other angleTo this

    fun signedAngleTo(other: Vector3, axis: Vector3): Double {
        val angle = angleTo(other)
        val cross = cross(other)
        val sign = sign(axis dot cross)
        return angle * sign
    }
    fun signedAngleTo(other: Vector2, axis: Vector3) = signedAngleTo(other.vec3, axis)

    /**
     * Cross product.
     * @return The cross product between this vector and [other].
     */
    infix fun cross(other: Vector2) = Vector3(-z * other.y, z * other.x, x * other.y - y * other.x)
    /**
     * Cross product.
     * @return The cross product between this vector and [other].
     */
    infix fun cross(other: Vector3) = Vector3(
        y * other.z - z * other.y,
        z * other.x - x * other.z,
        x * other.y - y * other.x
    )

    /**The projection of this vector onto [other].*/
    infix fun projectOn(other: Vector2) = other * dot(other) / other.sqrLength
    /**The projection of this vector onto [other].*/
    infix fun projectOn(other: Vector3) = other * dot(other) / other.sqrLength
    /**The projection of this vector onto [other].*/
    infix fun projectOn(other: Vector4) = other * dot(other) / other.sqrLength

    infix fun projectOntoPlane(normal: Vector3): Vector3 {
        val sqrMag = normal dot normal
        if (sqrMag == 0.0) return this
        val dot = dot(normal)
        return (this - normal) * dot / sqrMag
    }

    /**Reflects the vector from [normal].*/
    infix fun reflectFrom(normal: Vector3): Vector3 {
        val norm = normal.normalized
        val factor = -2.0 * this dot norm
        return factor * norm + this
    }
    /**Reflects the vector from [normal].*/
    infix fun reflectFrom(normal: Vector2) = reflectFrom(normal.vec3)
    /**Reflects the vector from [normal].*/
    infix fun reflectFrom(normal: Vector4) = vec4 reflectFrom normal

    /**
     * Rotates the vector by [angle] radians around [axis].
     * @param angle The desired angle in **radians**.
     * @param axis The desired **normalized** vector to rotate this vector around.
     * @return The rotated vector
     */
    fun rotatedBy(angle: Number, axis: Vector3) = Quaternion(angle, axis) * this

    override fun toString() = "{ $x; $y; $z }"
    override fun equals(other: Any?) = other is Vector3 && x == other.x && y == other.y && z == other.z
    override fun hashCode() = 31 * (31 * x.hashCode() + y.hashCode()) + z.hashCode()
}