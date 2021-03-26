package com.example.viewmodelobserver.screens.score

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.viewmodelobserver.R
import com.example.viewmodelobserver.databinding.ScoreFragmentBinding
import com.example.viewmodelobserver.screens.game.GameFragmentDirections

/**
 * Fragment where the final score is shown, after the game is over
 */
class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    private lateinit var binding: ScoreFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.score_fragment,
            container,
            false
        )
        viewModelFactory =
            ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ScoreViewModel::class.java)

        binding.scoreViewModel = viewModel

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })


//        binding.playAgainButton.setOnClickListener { viewModel.onPlayAgain() }

        viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer { playAgain ->
            Log.e("asdasd", "다시 들어오나??")
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                viewModel.onPlayAgainComplete()
            }

        })
        return binding.root
    }

}