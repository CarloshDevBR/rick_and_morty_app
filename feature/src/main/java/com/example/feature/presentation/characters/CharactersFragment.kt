package com.example.feature.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.feature.databinding.FragmentCharactersBinding
import com.example.feature.presentation.characters.adapter.CharactersAdapter
import com.example.feature.presentation.characters.adapter.CharactersLoadMoreStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels()

    private lateinit var charactersAdapter: CharactersAdapter

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
        initCharactersAdapter()
        setupListeners()
        setupCharactersAdapter()
        observerInitialLoadState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initCharactersAdapter() {
        charactersAdapter = CharactersAdapter()
        with(binding.recyclerCharacters) {
            setHasFixedSize(true)
            adapter = charactersAdapter
                .withLoadStateFooter(
                    footer = CharactersLoadMoreStateAdapter(charactersAdapter::retry)
                )
        }
    }

    private fun setupListeners() = with(binding) {
        includeViewCharactersErrorState.buttonRetry.setOnClickListener {
            charactersAdapter.refresh()
        }
    }

    private fun setupCharactersAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.charactersPagingData(EMPTY).collectLatest { pagingData ->
                    charactersAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun observerInitialLoadState() = with(binding.flipperCharacters) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                charactersAdapter.loadStateFlow.collectLatest { loadStates ->
                    displayedChild = when (loadStates.refresh) {
                        is LoadState.Loading -> {
                            setShimmerVisibility(true)
                            FLIPPER_LOADING
                        }
                        is LoadState.NotLoading -> {
                            setShimmerVisibility(false)
                            FLIPPER_CHARACTERS
                        }
                        is LoadState.Error -> {
                            setShimmerVisibility(false)
                            FLIPPER_ERROR
                        }
                    }
                }
            }
        }
    }

    private fun setShimmerVisibility(isVisible: Boolean) =
        with(binding.includeViewCharactersLoadingState.shimmerCharacters) {
            visibility = if (isVisible) View.VISIBLE else View.GONE
            if (isVisible) startShimmer() else stopShimmer()
        }

    private companion object {
        const val FLIPPER_LOADING = 0
        const val FLIPPER_CHARACTERS = 1
        const val FLIPPER_ERROR = 2
        const val EMPTY = ""
    }
}
