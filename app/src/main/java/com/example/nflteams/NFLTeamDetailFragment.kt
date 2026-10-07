package com.example.nflteams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nflteams.databinding.FragmentTeamDetailBinding

/**
 * Controller: displays the details for one NFLTeam, hosted by
 * MainActivity inside fragment_container.
 *
 * v.1 note: there's no list or navigation yet, so this fragment just
 * builds one hard-coded NFLTeam itself. In a later version this will
 * instead come in through a Bundle argument (the way CrimeFragment
 * receives a crime ID via newInstance() later in the book), once there's
 * a list of teams to choose from.
 */
class NFLTeamDetailFragment : Fragment() {

    // View Binding: nullable outside onCreateView()/onDestroyView() so we
    // never hold a view reference longer than the view itself exists.
    private var _binding: FragmentTeamDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var team: NFLTeam

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        team = NFLTeam(
            teamID = "IND",
            teamName = "Indianapolis Colts",
            logoFile = "colts",
            conference = "AFC",
            division = "South",
            stadium = "Lucas Oil Stadium",
            latitude = 39.7601,
            longitude = -86.1639
        )
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

        binding.teamName.text = team.teamName
        binding.teamDivision.text = "${team.conference} ${team.division}"
        binding.stadiumName.text = team.stadium

        // team_logo's image is set directly in fragment_team_detail.xml
        // via app:srcCompat, per the assignment's instruction not to load
        // it dynamically in code.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): NFLTeamDetailFragment {
            return NFLTeamDetailFragment()
        }
    }
}
