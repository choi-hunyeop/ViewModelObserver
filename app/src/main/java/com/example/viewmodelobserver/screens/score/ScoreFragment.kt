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

        //뷰모델과 xml에 data로 선언한 뷰모델을 연결
        // 뷰모델로 가져온 ScoreViewModel이나, data로 선언한 ScoreViewModel이랑 서로 같은 거임
        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
//        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
//            binding.scoreText.text = newScore.toString()
//        })

            //바인딩되어 xml에 직접 메소드연결함
//        binding.playAgainButton.setOnClickListener { viewModel.onPlayAgain() }

        viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                viewModel.onPlayAgainComplete()
            }

        })
        return binding.root
    }

}