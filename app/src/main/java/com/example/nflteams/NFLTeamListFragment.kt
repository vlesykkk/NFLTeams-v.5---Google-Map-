package com.example.nflteams

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nflteams.databinding.FragmentTeamListBinding
import com.example.nflteams.databinding.ListItemTeamBinding

/**
 * Controller: shows every NFLTeam in a scrolling RecyclerView, the same
 * way CrimeListFragment does in Chapter 10. The ViewHolder and Adapter
 * are inner classes, as in the book.
 *
 * v.4: clicking a row tells the hosting Activity which team was chosen
 * through the Callbacks interface (Chapter 13's pattern), so this
 * fragment never has to know how navigation is done.
 */
class NFLTeamListFragment : Fragment() {

    /** Hosting Activity must implement this. */
    interface Callbacks {
        fun onTeamSelected(teamID: String)
    }

    private var callbacks: Callbacks? = null

    private var _binding: FragmentTeamListBinding? = null
    private val binding get() = _binding!!

    private val teamListViewModel: NFLTeamListViewModel by lazy {
        ViewModelProvider(this)[NFLTeamListViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamListBinding.inflate(inflater, container, false)
        // A RecyclerView draws nothing without a LayoutManager.
        binding.nflteamRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI() {
        binding.nflteamRecyclerView.adapter = TeamAdapter(teamListViewModel.teams)
    }

    private inner class TeamHolder(private val itemBinding: ListItemTeamBinding) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        private lateinit var team: NFLTeam

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            callbacks?.onTeamSelected(team.teamID)
        }

        fun bind(team: NFLTeam) {
            this.team = team
            itemBinding.listTeamName.text = team.teamName
            itemBinding.listTeamStadium.text = team.stadium
        }
    }

    private inner class TeamAdapter(private val teams: List<NFLTeam>) :
        RecyclerView.Adapter<TeamHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
            val itemBinding = ListItemTeamBinding.inflate(layoutInflater, parent, false)
            return TeamHolder(itemBinding)
        }

        override fun getItemCount() = teams.size

        override fun onBindViewHolder(holder: TeamHolder, position: Int) {
            holder.bind(teams[position])
        }
    }

    companion object {
        fun newInstance(): NFLTeamListFragment {
            return NFLTeamListFragment()
        }
    }
}
