package com.example.mvvmshoppinglist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmshoppinglist.data.db.entity.ShoppingItem
import com.example.mvvmshoppinglist.databinding.ShoppingItemBinding

class ShoppingItemAdapter() : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    private val items: MutableList<ShoppingItem> = mutableListOf()
    var deleteClickListener: (ShoppingItem) -> Unit = {}
    var plusClickListener: (ShoppingItem) -> Unit = {}
    var minusClickListener: (ShoppingItem) -> Unit = {}

    fun addItems(items: List<ShoppingItem>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun replaceItems(items: List<ShoppingItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val binding = ShoppingItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ShoppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]
        holder.bindData(currentShoppingItem)
    }

    override fun getItemCount(): Int = items.size

    inner class ShoppingViewHolder(private val binding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: ShoppingItem) {
            with(binding) {
                tvName.text = item.name
                tvAmount.text = "${item.amount}"
                ivDelete.setOnClickListener {
                    deleteClickListener.invoke(item)
                }
                ivPlus.setOnClickListener {
                    plusClickListener.invoke(item)
                }
                ivMinus.setOnClickListener {
                    minusClickListener.invoke(item)
                }
            }
        }
    }
}