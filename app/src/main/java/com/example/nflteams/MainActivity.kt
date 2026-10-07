package com.example.nflteams

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Controller: hosts NFLTeamDetailFragment inside the fragment_container
 * FrameLayout defined in activity_main.xml, exactly as CrimeActivity
 * does for CrimeFragment in Chapter 9.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Only add the fragment the first time this Activity is created.
        // On a configuration change (e.g. rotation), the FragmentManager
        // already has it, and adding it again would create a duplicate.
        val currentFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, NFLTeamDetailFragment.newInstance())
                .commit()
        }
    }
}
