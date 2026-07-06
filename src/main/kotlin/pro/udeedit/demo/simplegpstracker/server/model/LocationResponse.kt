package pro.udeedit.demo.simplegpstracker.server.model

import kotlinx.serialization.Serializable

/**
 * JSON response returned after processing a location payload.
 *
 * @param status Short status code, e.g. "ok" or "error".
 * @param message Human-readable message describing the result.
 */
@Serializable
data class LocationResponse(
    val status: String,
    val message: String
)
