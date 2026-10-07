package com.example.nflteams

/**
 * Model: one NFL team's data, matching the Object Diagram's Model layer
 * exactly (teamID, teamName, logoFile, conference, division, stadium,
 * latitude, longitude).
 *
 * latitude/longitude aren't used yet in v.1 (no MapView), but they're
 * kept here since we'll need them when the map is added in a later
 * version.
 */
data class NFLTeam(
    val teamID: String,
    val teamName: String,
    val logoFile: String,
    val conference: String,
    val division: String,
    val stadium: String,
    val latitude: Double,
    val longitude: Double
)
