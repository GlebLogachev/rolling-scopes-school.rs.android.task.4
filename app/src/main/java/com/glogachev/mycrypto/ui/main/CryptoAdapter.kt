package com.glogachev.mycrypto.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.glogachev.mycrypto.R
import com.glogachev.mycrypto.databinding.CryptoInfoItemBinding
import com.glogachev.mycrypto.domain.model.Crypto

class CryptoAdapter(
    private val listener: (Crypto) -> Unit
) : ListAdapter<Crypto, CryptoViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CryptoViewHolder(CryptoInfoItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) =
        holder.bind(getItem(position), listener)
}

class CryptoViewHolder(private val binding: CryptoInfoItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Crypto, listener: (Crypto) -> Unit) {
        val context = binding.root.context
        binding.apply {
            tvCoinName.text = item.name
            investmentAmount.text =
                context.getString(R.string.investment_amount, item.investmentAmount)
            tvQuantityValue.text = item.quantity.toString()
            tvPriceValue.text = item.price.toString()

            root.setOnClickListener {
                listener(item)
            }
        }
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<Crypto>() {
    override fun areItemsTheSame(oldItem: Crypto, newItem: Crypto): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Crypto, newItem: Crypto): Boolean =
        oldItem == newItem
}