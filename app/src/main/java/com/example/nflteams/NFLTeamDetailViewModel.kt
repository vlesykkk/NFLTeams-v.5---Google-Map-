package com.example.nflteams

import androidx.lifecycle.ViewModel

/**
 * Looks up one NFLTeam by its teamID from the app's local team data
 * (there is no database in this app), the way CrimeDetailViewModel
 * supplies a crime to CrimeFragment in Chapter 12.
 */
class NFLTeamDetailViewModel : ViewModel() {

    private val teams: List<NFLTeam> = NFLTeamListViewModel().teams

    var team: NFLTeam? = null
        private set

    fun loadTeam(teamID: String) {
        team = teams.firstOrNull { it.teamID == teamID }
    }
}
