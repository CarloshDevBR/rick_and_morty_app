package com.example.feature.presentation.characters.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.CharacterData
import com.example.feature.presentation.characters.holder.CharactersViewHolder

class CharactersAdapter : PagingDataAdapter<CharacterData, CharactersViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
        CharactersViewHolder.create(parent)

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    private companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<CharacterData>() {
            override fun areItemsTheSame(
                oldItem: CharacterData,
                newItem: CharacterData
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CharacterData,
                newItem: CharacterData
            ): Boolean = oldItem.id == newItem.id
        }
    }
}
