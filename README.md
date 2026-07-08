# SimpleGpsTrackerServer (Ktor backend)

SimpleGpsTrackerServer is a small Ktor-based backend that receives location data from the
SimpleGpsTracker Android app and responds with a JSON status.

The project is designed as a portfolio-quality example of a modern Kotlin server deployed to Google Cloud Run.

---

## Features

- Ktor server running on JVM
- JSON API:
    - POST /api/v1/locations – accepts location updates from clients
- JSON contracts shared conceptually with the Android app:
    - LocationPayload – incoming location sample
    - LocationResponse – simple JSON status response
- Logging of all received locations
- Dockerfile for containerized deployment
- Ready for Google Cloud Run deployment

---

## Tech Stack

- Kotlin (JVM)
- Ktor server (Netty engine)
- Kotlinx Serialization (JSON)
- Docker
- Google Cloud Run

---

## JSON Contracts

### Request (Android → Server)

Endpoint

text
POST {BASE_URL}/api/v1/locations §

Body (LocationPayload)

json
{
"deviceId": "330be5e7-bef9-47f8-aeb3-5823aac7e6b2",
"userName": "SargiusPro",
"latitude": 30.8605606,
"longitude": -100.6009299,
"accuracyMeters": 8.3,
"timestampMillis": 1783347145469
}

- deviceId: stable user/device ID (UUID)
- userName: optional user-friendly name
- latitude, longitude: decimal degrees
- accuracyMeters: optional accuracy radius in meters
- timestampMillis: UNIX time in milliseconds (UTC)

### Response (Server → Android)

Body (LocationResponse)

json
{
"status": "ok",
"message": "Location received"
}

The Android app logs the HTTP status and full JSON body for each request.

---

### Running Locally

1. Clone the repository and open in IntelliJ IDEA.
2. Run the server:

   bash
   ./gradlew run

3. Test the endpoint:

   bash
   curl -X POST http://localhost:8080/api/v1/locations \
   -H "Content-Type: application/json" \
   -d '{
   "deviceId": "test-device",
   "userName": "Test User",
   "latitude": 55.75,
   "longitude": 37.62,
   "accuracyMeters": 10.5,
   "timestampMillis": 1730180234123
   }'

You should receive:

json
{"status":"ok","message":"Location received"}

---

## Docker & Cloud Run (example)

Build and tag image:

bash
docker build -t simplegpsserver .
docker tag simplegpsserver \
us-central1-docker.pkg.dev/<PROJECT_ID>/gps-tracker-repo/simplegpsserver

Push and deploy:

bash
docker push \
us-central1-docker.pkg.dev/<PROJECT_ID>/gps-tracker-repo/simplegpsserver

gcloud run deploy simplegpsserver \
--image us-central1-docker.pkg.dev/<PROJECT_ID>/gps-tracker-repo/simplegpsserver \
--platform managed \
--region us-central1 \
--allow-unauthenticated \
--port 8080



Your public base URL will look like:

text
https://simplegpsserver-xxxxxxxxxx-uc.a.run.app

The Android app should use this as its base URL (and will POST to /api/v1/locations).

---

## License

MIT