package com.example.viewmodelobserver.screens.game

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
import com.example.viewmodelobserver.databinding.GameFragmentBinding

class GameFragment : Fragment(){

    private lateinit var binding: GameFragmentBinding


    private lateinit var viewModel: GameViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )


        Log.i("GameFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        //데이터바인딩으로 xml에 ViewModel을 직접 설정함. 필요없게 됨
//        binding.correctButton.setOnClickListener { onCorrect() }
//        binding.skipButton.setOnClickListener { onSkip() }
//        binding.endGameButton.setOnClickListener{
//            onEndGame()
//        }


        //뷰모델과 xml에 data로 선언한 뷰모델을 연결
        // 뷰모델로 가져온 GameViewModel이나, data로 선언한 GameViewModel이랑 서로 같은 거임
        binding.gameViewModel = viewModel
        //gameViewModel을 초기화 한 후 조각보기를 바인딩 변수의 수명주기 소유자로 설정합니다.
        // 이것은 위의 LiveData 객체의 범위를 정의하여 객체가 game_fragment.xml
        // 레이아웃의 뷰를 자동으로 업데이트 할 수 있도록합니다.
        binding.lifecycleOwner = viewLifecycleOwner
        //덕분에 없앨수있음
//        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
//            binding.wordText.text = newWord
//        })


        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })
        viewModel.eventGameFinish.observe(viewLifecycleOwner, { hasFinished ->
            if(hasFinished) gameFinished()
        })
        return binding.root

    }

    // 데이터 바인딩으로 뷰모델객체로 접근하던 부분이 필요없어짐
//    /** Methods for button click handlers **/
//    private fun onSkip() { viewModel.onSkip() }
//    private fun onCorrect() { viewModel.onCorrect() }
//    private fun onEndGame() { gameFinished() }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action = GameFragmentDirections.actionGameToScore()
        action.score = viewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onGameFinishComplete()
    }
}