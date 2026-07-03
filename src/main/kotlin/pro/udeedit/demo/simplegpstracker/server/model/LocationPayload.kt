package pro.udeedit.demo.simplegpstracker.server.model

import kotlinx.serialization.Serializable

/**
 * Payload sent by the Android client for one location sample.
 */
@Serializable
data class LocationPayload(
    val deviceId: String,
    val latitude: Double,
    val longitude: Double,
    val accuracyMeters: Float? = null,
    val timestampMillis: Long
)
