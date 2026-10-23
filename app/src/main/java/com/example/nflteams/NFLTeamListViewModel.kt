package com.example.nflteams

import androidx.lifecycle.ViewModel

/**
 * PLACEHOLDER -- REPLACE WITH THE INSTRUCTOR'S PROVIDED FILE.
 *
 * The assignment supplies the real NFLTeamListViewModel (linked in the
 * PDF). Paste its contents over this file. If its list property isn't
 * called `teams`, change the one reference in NFLTeamListFragment.updateUI().
 */
class NFLTeamListViewModel : ViewModel() {

    val teams = listOf(
        NFLTeam("CHI", "Chicago Bears", "bears", "NFC", "North", "Soldier Field", 41.8623, -87.6167),
        NFLTeam("CIN", "Cincinnati Bengals", "bengals", "AFC", "North", "Paul Brown Stadium", 39.0955, -84.5161),
        NFLTeam("BUF", "Buffalo Bills", "bills", "AFC", "East", "Bills Stadium", 42.7738, -78.7870),
        NFLTeam("DEN", "Denver Broncos", "broncos", "AFC", "West", "Empower Field at Mile High", 39.7439, -105.0201),
        NFLTeam("CLE", "Cleveland Browns", "browns", "AFC", "North", "FirstEnergy Stadium", 41.5061, -81.6995),
        NFLTeam("TB", "Tampa Bay Buccaneers", "buccaneers", "NFC", "South", "Raymond James Stadium", 27.9759, -82.5033),
        NFLTeam("ARI", "Arizona Cardinals", "cardinals", "NFC", "West", "State Farm Stadium", 33.5276, -112.2626),
        NFLTeam("LAC", "Los Angeles Chargers", "chargers", "AFC", "West", "SoFi Stadium", 33.9535, -118.3392),
        NFLTeam("KC", "Kansas City Chiefs", "chiefs", "AFC", "West", "Arrowhead Stadium", 39.0489, -94.4839)
    )
}
