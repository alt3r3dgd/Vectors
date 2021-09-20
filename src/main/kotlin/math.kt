package me.altered.vectors

import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min

/** The vector with minimum components of vectors [a] and [b]. */
fun min(a: Vector2, b: Vector2) = Vector2(min(a.x, b.x), min(a.y, b.y))
/** The vector with minimum components of vectors [a] and [b]. */
fun min(a: Vector2, b: Vector3) = Vector3(min(a.x, b.x), min(a.y, b.y), min(0.0, b.z))
/** The vector with minimum components of vectors [a] and [b]. */
fun min(a: Vector3, b: Vector2) = Vector3(min(a.x, b.x), min(a.y, b.y), min(0.0, a.z))
/** The vector with minimum components of vectors [a] and [b]. */
fun min(a: Vector3, b: Vector3) = Vector3(min(a.x, b.x), min(a.y, b.y), min(a.z, b.z))
/** The vector with minimum components of vectors [a] and [b]. */
fun min(a: Vector4, b: Vector4) = Vector4(min(a.x, b.x), min(a.y, b.y), min(a.z, b.z), min(a.w, b.w))
/** The vector with minimum components of vectors [a] and [b]. */
fun min(a: Vector4, b: Vector3) = Vector4(min(a.x, b.x), min(a.y, b.y), min(a.z, b.z), min(a.w, 0.0))
/** The vector with minimum components of vectors [a] and [b]. */
fun min(a: Vector4, b: Vector2) = Vector4(min(a.x, b.x), min(a.y, b.y), min(a.z, 0.0), min(a.w, 0.0))
/** The vector with minimum components of vectors [a] and [b]. */
fun min(a: Vector3, b: Vector4) = Vector4(min(a.x, b.x), min(a.y, b.y), min(a.z, b.z), min(0.0, b.w))
/** The vector with minimum components of vectors [a] and [b]. */
fun min(a: Vector2, b: Vector4) = Vector4(min(a.x, b.x), min(a.y, b.y), min(0.0, b.z), min(0.0, b.w))
/** The quaternion with minimum components of quaternions [a] and [b]. */
fun min(a: Quaternion, b: Quaternion) = Quaternion(min(a.x, b.x), min(a.y, b.y), min(a.z, b.z), min(a.w, b.w))

/** The vector with maximum components of vectors [a] and [b]. */
fun max(a: Vector2, b: Vector2) = Vector2(max(a.x, b.x), max(a.y, b.y))
/** The vector with maximum components of vectors [a] and [b]. */
fun max(a: Vector2, b: Vector3) = Vector3(max(a.x, b.x), max(a.y, b.y), max(0.0, b.z))
/** The vector with maximum components of vectors [a] and [b]. */
fun max(a: Vector3, b: Vector2) = Vector3(max(a.x, b.x), max(a.y, b.y), max(0.0, a.z))
/** The vector with maximum components of vectors [a] and [b]. */
fun max(a: Vector3, b: Vector3) = Vector3(max(a.x, b.x), max(a.y, b.y), max(a.z, b.z))
/** The vector with maximum components of vectors [a] and [b]. */
fun max(a: Vector4, b: Vector4) = Vector4(max(a.x, b.x), max(a.y, b.y), max(a.z, b.z), max(a.w, b.w))
/** The vector with maximum components of vectors [a] and [b]. */
fun max(a: Vector4, b: Vector3) = Vector4(max(a.x, b.x), max(a.y, b.y), max(a.z, b.z), max(a.w, 0.0))
/** The vector with maximum components of vectors [a] and [b]. */
fun max(a: Vector4, b: Vector2) = Vector4(max(a.x, b.x), max(a.y, b.y), max(a.z, 0.0), max(a.w, 0.0))
/** The vector with maximum components of vectors [a] and [b]. */
fun max(a: Vector3, b: Vector4) = Vector4(max(a.x, b.x), max(a.y, b.y), max(a.z, b.z), max(0.0, b.w))
/** The vector with maximum components of vectors [a] and [b]. */
fun max(a: Vector2, b: Vector4) = Vector4(max(a.x, b.x), max(a.y, b.y), max(0.0, b.z), max(0.0, b.w))
/** The quaternion with maximum components of quaternions [a] and [b]. */
fun max(a: Quaternion, b: Quaternion) = Quaternion(max(a.x, b.x), max(a.y, b.y), max(a.z, b.z), max(a.w, b.w))

/** Linear interpolation between vectors [a] and [b] by [weight]. */
fun lerp(a: Vector2, b: Vector2, weight: Double) = a * (1 - weight) + b * weight
/** Linear interpolation between vectors [a] and [b] by [weight]. */
fun lerp(a: Vector2, b: Vector3, weight: Double) = a * (1 - weight) + b * weight
/** Linear interpolation between vectors [a] and [b] by [weight]. */
fun lerp(a: Vector3, b: Vector2, weight: Double) = a * (1 - weight) + b * weight
/** Linear interpolation between vectors [a] and [b] by [weight]. */
fun lerp(a: Vector3, b: Vector3, weight: Double) = a * (1 - weight) + b * weight
/** Linear interpolation between vectors [a] and [b] by [weight]. */
fun lerp(a: Vector4, b: Vector4, weight: Double) = a * (1 - weight) + b * weight
/** Linear interpolation between vectors [a] and [b] by [weight]. */
fun lerp(a: Vector4, b: Vector3, weight: Double) = a * (1 - weight) + b * weight
/** Linear interpolation between vectors [a] and [b] by [weight]. */
fun lerp(a: Vector4, b: Vector2, weight: Double) = a * (1 - weight) + b * weight
/** Linear interpolation between vectors [a] and [b] by [weight]. */
fun lerp(a: Vector3, b: Vector4, weight: Double) = a * (1 - weight) + b * weight
/** Linear interpolation between vectors [a] and [b] by [weight]. */
fun lerp(a: Vector2, b: Vector4, weight: Double) = a * (1 - weight) + b * weight
/** Linear interpolation between quaternions [a] and [b] by [weight]. */
fun lerp(a: Quaternion, b: Quaternion, weight: Double) = a * (1 - weight) + b * weight

operator fun Number.times(vec: Vector2) = vec * this
operator fun Number.times(vec: Vector3) = vec * this
operator fun Number.times(vec: Vector4) = vec * this

operator fun Number.div(vec: Vector2) = Vector2(toDouble() / vec.x, toDouble() / vec.y)
operator fun Number.div(vec: Vector3) = Vector3(toDouble() / vec.x, toDouble() / vec.y, toDouble() / vec.z)
operator fun Number.div(vec: Vector4) =
    Vector4(toDouble() / vec.x, toDouble() / vec.y, toDouble() / vec.z, toDouble() / vec.w)

operator fun Number.rem(vec: Vector2) = Vector2(toDouble() % vec.x, toDouble() % vec.y)
operator fun Number.rem(vec: Vector3) = Vector3(toDouble() % vec.x, toDouble() % vec.y, toDouble() % vec.z)
operator fun Number.rem(vec: Vector4) =
    Vector4(toDouble() % vec.x, toDouble() % vec.y, toDouble() % vec.z, toDouble() % vec.w)

val Double.degrees get() = this / PI * 180
val Double.radians get() = this * PI / 180