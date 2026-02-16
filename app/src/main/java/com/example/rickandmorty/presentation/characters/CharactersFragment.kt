package com.example.rickandmorty.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.model.CharacterData
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.presentation.characters.adapter.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private val adapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharactersBinding.inflate(
            inflater,
            container,
            false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCharactersAdapter()
    }

    private fun setupCharactersAdapter() = with(binding) {
        recyclerCharacters.adapter = adapter
        adapter.submitList(
            listOf(
                CharacterData(
                    id = 1,
                    name = "Tusked Assassin",
                    imageUrl = "https://rickandmortyapi.com/api/character/avatar/369.jpeg",
                ),
                CharacterData(
                    id = 2,
                    name = "Tusked Assassin",
                    imageUrl = "https://rickandmortyapi.com/api/character/avatar/369.jpeg",
                ),
                CharacterData(
                    id = 3,
                    name = "Tusked Assassin",
                    imageUrl = "https://rickandmortyapi.com/api/character/avatar/369.jpeg",
                )
            )
        )
    }
}
