package com.shem.adv160420033week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText


class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txtTurn = view.findViewById<TextView>(R.id.txtTurn)
        val txtQuestion = view.findViewById<TextView>(R.id.txtQuestion)
        val txtAnswer = view.findViewById<TextInputEditText>(R.id.txtAnswer)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        if(arguments != null){
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }
        var num1 = (1..100).random()
        var num2 = (1..100).random()
        var answer = num1 + num2
        var score = 0
        txtQuestion.text = "$num1 + $num2"
        btnSubmit.setOnClickListener {
            val playerAnswer = Integer.parseInt(txtAnswer.text.toString())
            if(playerAnswer == answer){
                score+=1
                num1 = (1..100).random()
                num2 = (1..100).random()
                answer = num1 + num2
                txtQuestion.text = "$num1 + $num2"
                txtAnswer.text?.clear()
            } else {
                val action = GameFragmentDirections.actionResultFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }

    }
}