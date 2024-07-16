package com.dicoding.asclepius.view.history

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.databinding.HistoryItemBinding
import com.dicoding.asclepius.data.Result
import com.dicoding.asclepius.view.history.HistoryAdapter.HistoryViewHolder
import androidx.recyclerview.widget.ListAdapter
import com.dicoding.asclepius.view.ResultActivity


class HistoryAdapter : ListAdapter<Result, HistoryViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem

            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val result: Result = getItem(position)
        holder.bind(result)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ResultActivity::class.java)
            intent.putExtra(ResultActivity.EXTRA_IMAGE_URI, result.image)
            intent.putExtra(ResultActivity.EXTRA_RESULT_LABEL, result.label)
            intent.putExtra(ResultActivity.EXTRA_RESULT_SCORE, result.score)
            holder.itemView.context.startActivity(intent)
        }
    }


    class HistoryViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            binding.ivResult.setImageURI(Uri.parse(result.image))
            binding.tvLabel.text = result.label
            binding.tvScore.text = result.score.toString()
        }
    }

}