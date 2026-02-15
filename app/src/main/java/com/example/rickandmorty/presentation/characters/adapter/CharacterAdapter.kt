package com.example.rickandmorty.presentation.characters.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.CharacterData
import com.example.rickandmorty.presentation.characters.holder.CharacterViewHolder

class CharacterAdapter : ListAdapter<CharacterData, CharacterViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder.create(parent)

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(getItem(position))

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
