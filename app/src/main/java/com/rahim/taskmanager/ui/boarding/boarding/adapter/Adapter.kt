package com.rahim.taskmanager.ui.boarding.boarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.rahim.taskmanager.R
import com.rahim.taskmanager.databinding.BoardItemBinding
import com.rahim.taskmanager.loadIMage
import com.rahim.taskmanager.model.Model

class Adapter (private val onClick:() -> Unit): RecyclerView.Adapter<Adapter.OnBoardingViewHolder>() {
    private val data = arrayListOf(
        Model("СЛЕДИТЕ ЗА СОБОЙ!", "правильно распределяйте время", R.raw.task1),
        Model("ПРИЛОЖЕНИН ПОМОЖЕТ!", "следи за поставленными задачами",R.raw.task2),
        Model("СТАНОВИТЕСЬ КАЖДЫЙ ДЕНЬ ЛУЧШЕ!","становитесь лучше",R.raw.task3)

    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(BoardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class OnBoardingViewHolder(private val binding:BoardItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(model: Model) {
            binding.boardTask.text = model.title
            binding.descText.text = model.decs
            model.image?.let{ binding.boardImg.setAnimation(it)}
            binding.btnStartApp.isVisible = adapterPosition == data.lastIndex
            binding.btnStartApp.setOnClickListener{
                onClick()
            }
        }

    }


}
