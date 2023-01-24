package com.example.harrypottercharacters.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypottercharacters.databinding.GridViewItemBinding
import com.example.harrypottercharacters.network.HpProperty

class PhotoGridAdapter(private val onClickListener: OnClickListener) : ListAdapter<HpProperty, PhotoGridAdapter.HpPropertyViewHolder>(DiffCallback) {
    class HpPropertyViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(hpProperty: HpProperty){
                binding.property = hpProperty
                binding.executePendingBindings()
            }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<HpProperty>() {
        override fun areItemsTheSame(oldItem: HpProperty, newItem: HpProperty): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HpProperty, newItem: HpProperty): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):PhotoGridAdapter.HpPropertyViewHolder {
        return HpPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder:PhotoGridAdapter.HpPropertyViewHolder, position:Int) {
        val hpProperty= getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(hpProperty)
        }
        holder.bind(hpProperty)
    }
    class OnClickListener(val  clickListener:(hpProperty:HpProperty)->Unit){
        fun onClick(hpProperty: HpProperty) = clickListener(hpProperty)
    }
}

