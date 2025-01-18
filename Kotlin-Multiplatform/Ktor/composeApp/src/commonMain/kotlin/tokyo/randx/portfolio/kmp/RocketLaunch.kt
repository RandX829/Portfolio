package tokyo.randx.portfolio.kmp

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunch(
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("name")
    val name: String,
    @SerialName("date_utc")
    val date: String,
    @SerialName("details")
    val details: String?,
    @SerialName("success")
    val isSuccess: Boolean?,
    @SerialName("links")
    val links: Links
) {
    val launchYear = Instant.parse(date).toLocalDateTime(TimeZone.UTC).year
}

@Serializable
data class Links(
    @SerialName("patch")
    val patch: Patch?,
    @SerialName("article")
    val article: String?
)

@Serializable
data class Patch(
    @SerialName("small")
    val small: String?,
    @SerialName("large")
    val large: String?
)