package com.example.nflteams

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Controller: hosts whichever fragment is currently showing inside the
 * fragment_container FrameLayout. It starts with the team list and, when
 * the list reports a selection through Callbacks, swaps in the detail
 * fragment for that team.
 */
class MainActivity : AppCompatActivity(), NFLTeamListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Only add the list the first time this Activity is created.
        // After rotation the FragmentManager already restores whichever
        // fragment (list or detail) was showing.
        val currentFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, NFLTeamListFragment.newInstance())
                .commit()
        }
    }

    override fun onTeamSelected(teamID: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NFLTeamDetailFragment.newInstance(teamID))
            // Puts the list on the back stack so Back returns to it.
            .addToBackStack(null)
            .commit()
    }
}
