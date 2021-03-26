package com.example.viewmodelobserver.screens.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.viewmodelobserver.R
import com.example.viewmodelobserver.databinding.TitleFragmentBinding

/**
 * Fragment for the starting or title screen of the app
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val binding: TitleFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.title_fragment, container, false)

        binding.playGameButton.setOnClickListener {
//            findNavController().navigate(TitleFragmentDirections.actionTitleToGame())
            findNavController().navigate(R.id.action_title_to_game)
        }
        return binding.root
    }
}