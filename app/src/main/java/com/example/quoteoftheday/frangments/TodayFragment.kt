package com.example.quoteoftheday.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.quoteoftheday.Quote
import com.example.quoteoftheday.database.QuoteDatabaseHelper
import com.example.quoteoftheday.databinding.FragmentTodayBinding

class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null
    private val binding get() = _binding!!

    private val quotes = listOf(
        Quote(text = "The best way to predict the future is to invent it.", author = "Alan Kay"),
        Quote(text = "Stay hungry, stay foolish.", author = "Steve Jobs"),
        Quote(text = "Life is what happens when you're busy making other plans.", author = "John Lennon"),
        Quote(text = "You miss 100% of the shots you don't take.", author = "Wayne Gretzky")
    )

    private lateinit var selectedQuote: Quote
    private lateinit var db: QuoteDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = QuoteDatabaseHelper(requireContext())
        selectedQuote = quotes.random()

        binding.quoteText.text = "\"${selectedQuote.text}\""
        binding.quoteAuthor.text = "- ${selectedQuote.author}"

        binding.saveButton.setOnClickListener {
            db.addQuote(selectedQuote)
            Toast.makeText(requireContext(), "Saved to favorites!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
