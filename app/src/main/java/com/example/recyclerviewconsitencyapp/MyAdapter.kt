package com.example.recyclerviewconsitencyapp

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclerviewconsitencyapp.databinding.ItemLayoutBinding

class MyAdapter : ListAdapter<Item, MyAdapter.MyViewHolder>(DiffUtils) {

    inner class MyViewHolder(val binding : ItemLayoutBinding) : ViewHolder(binding.root){

        var textWatcher1 : TextWatcher? = null

        fun bind(item : Item){


            textWatcher1?.let { binding.editText.removeTextChangedListener(textWatcher1) }

            textWatcher1 = null
            binding.editText.setText(item.text)
            textWatcher1 = object : TextWatcher{
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(text: Editable?) {
                    item.text = text.toString()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            }
            binding.editText.addTextChangedListener(textWatcher1)



        }
    }


    object DiffUtils : DiffUtil.ItemCallback<Item>(){
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val item = getItem(position)
        holder.bind(item)
    }

}