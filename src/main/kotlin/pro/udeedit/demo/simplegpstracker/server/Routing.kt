package pro.udeedit.demo.simplegpstracker.server

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pro.udeedit.demo.simplegpstracker.server.model.LocationPayload
import pro.udeedit.demo.simplegpstracker.server.model.LocationResponse

/**
 * Configures HTTP routing for the SimpleGpsTrackerServer.
 *
 * Exposes:
 * - GET "/"                    – simple health check ("Hello, World!")
 * - POST "/api/v1/locations"   – accepts a [LocationPayload] and returns
 *                                a JSON [LocationResponse].
 */
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, World! You are on the root of Simple Gps Tracker server.")
        }

        route("/api/v1") {
            post("/locations") {
                val payload = call.receive<LocationPayload>()
                log.info("Received location: $payload")

                call.respond(
                    HttpStatusCode.OK,
                    LocationResponse(
                        status = "ok",
                        message = "Location received"
                    )
                )
            }
        }
    }
}
