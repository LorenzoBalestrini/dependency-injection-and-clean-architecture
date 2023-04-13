package com.example.myapplicationwithauthorization.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplicationwithauthorization.MyApplication
import com.example.myapplicationwithauthorization.R
import com.example.myapplicationwithauthorization.databinding.FragmentMainBinding
import com.example.myapplicationwithauthorization.network.usecase.MainViewModel
import com.example.myapplicationwithauthorization.network.usecase.model.TriviaQuestion
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        viewModel = (requireActivity().application as MyApplication).
        mainViewModelFactory.create(MainViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeDetails()
        viewModel.retrieveDetails()

        binding.buttonSetQuestion.setOnClickListener {
            viewModel.retrieveDetails()
            binding.textAnswer.visibility = View.INVISIBLE
        }

        binding.buttonShowAnswer.setOnClickListener {
            binding.textAnswer.visibility = View.VISIBLE
        }
    }

    private fun observeDetails() {

        viewModel.result.observe(viewLifecycleOwner) {
            when(it) {
                is MainViewModel.TriviaViewModelEvent.TriviaResult -> setQuestion(it.question)
                is MainViewModel.TriviaViewModelEvent.TriviaError -> Snackbar.make(
                    binding.fragmentMain,
                    "$it",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry") { observeDetails() }.show()
                is MainViewModel.TriviaViewModelEvent.LastQuestion -> Toast.makeText(
                    requireContext(),
                    "First time opening the app?",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setQuestion(question: List<TriviaQuestion>) {
        binding.textCategory.text = getString(
            R.string.category,
            question.firstOrNull()?.category ?: "-"
        )
        binding.textQuestion.text = getString(
            R.string.question,
            question.firstOrNull()?.question ?: "-"
        )
        binding.textAnswer.text = getString(
            R.string.answer,
            question.firstOrNull()?.answer ?: "-"
        )
    }

}