package com.example.nflteams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.nflteams.databinding.FragmentTeamDetailBinding

private const val ARG_TEAM_ID = "team_id"
private const val MAP_ZOOM = 15f // street/neighborhood level: stadium and nearby roads

/**
 * Controller: displays the details for one NFLTeam. The team's ID comes
 * in through the fragment arguments (set by newInstance(teamID)), the
 * same way CrimeFragment receives a crime ID in Chapter 13.
 */
class NFLTeamDetailFragment : Fragment() {

    private var _binding: FragmentTeamDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: NFLTeamDetailViewModel by lazy {
        ViewModelProvider(this)[NFLTeamDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val teamID = arguments?.getString(ARG_TEAM_ID) ?: ""
        detailViewModel.loadTeam(teamID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // A MapView needs onCreate() called before anything else touches it.
        binding.stadiumMap.onCreate(savedInstanceState)

        val team = detailViewModel.team ?: return

        binding.teamName.text = team.teamName
        binding.teamDivision.text = "${team.conference} ${team.division}"
        binding.stadiumName.text = team.stadium

        // The logo now depends on which team was picked, so it is looked
        // up by file name (team.logoFile) in res/drawable. If that
        // drawable hasn't been added yet, the ImageView stays empty
        // instead of crashing.
        val logoId = resources.getIdentifier(
            // Drawable names have no extension, so "colts.png" becomes "colts"
            team.logoFile.substringBefore('.').lowercase(),
            "drawable",
            requireContext().packageName
        )
        if (logoId != 0) {
            binding.teamLogo.setImageResource(logoId)
        }

        // Ask for the GoogleMap; the callback runs once the map is ready.
        binding.stadiumMap.getMapAsync { googleMap ->
            val stadiumLocation = LatLng(team.latitude, team.longitude)
            // Marker at the stadium (tap it to see the stadium name)
            googleMap.addMarker(
                MarkerOptions().position(stadiumLocation).title(team.stadium)
            )
            // Move to the stadium and zoom in
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(stadiumLocation, MAP_ZOOM)
            )
        }
    }

    // A MapView must be told about each lifecycle event of its fragment.
    override fun onStart() {
        super.onStart()
        _binding?.stadiumMap?.onStart()
    }

    override fun onResume() {
        super.onResume()
        _binding?.stadiumMap?.onResume()
    }

    override fun onPause() {
        _binding?.stadiumMap?.onPause()
        super.onPause()
    }

    override fun onStop() {
        _binding?.stadiumMap?.onStop()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        _binding?.stadiumMap?.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        _binding?.stadiumMap?.onLowMemory()
    }

    override fun onDestroyView() {
        _binding?.stadiumMap?.onDestroy()
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(teamID: String): NFLTeamDetailFragment {
            return NFLTeamDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TEAM_ID, teamID)
                }
            }
        }
    }
}
