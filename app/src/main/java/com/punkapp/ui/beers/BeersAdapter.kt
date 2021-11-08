package com.punkapp.ui.beers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.punkapp.beers.Beer
import com.punkapp.databinding.BeerListItemBinding

class BeersAdapter : RecyclerView.Adapter<BeersAdapter.BeerViewHolder>() {

    private var items: List<Beer> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        return BeerViewHolder(
            BeerListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val beer = items[position]
        holder.bind(beer)
    }

    override fun getItemCount(): Int = items.size // TODO: Add +2 for loading next page

    fun appendItems(newItems: List<Beer>) {
        items = newItems
        notifyDataSetChanged()
    }

    class BeerViewHolder(
        private val binding: BeerListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Beer) {
            binding.apply {
                beer = item
                executePendingBindings()
            }
        }
    }
}

private class BeerDiffCallback : DiffUtil.ItemCallback<Beer>() {
    override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem == newItem
    }
}