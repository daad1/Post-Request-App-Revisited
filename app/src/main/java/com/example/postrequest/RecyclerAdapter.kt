package com.example.postrequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postrequest.databinding.ItemRowBinding

class RecyclerAdapter(private var users: Users): RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = users[position]

        holder.binding.apply {
            tvName.text = user.name
            tvLoaction.text = user.location
        }
    }

    override fun getItemCount() = users.size

    fun update(users: Users){
        this.users = users
        notifyDataSetChanged()
    }
}