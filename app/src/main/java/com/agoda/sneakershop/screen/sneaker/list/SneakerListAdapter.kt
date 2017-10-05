package com.agoda.sneakershop.screen.sneaker.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.agoda.sneakershop.R
import com.agoda.sneakershop.common.extension.format
import com.agoda.sneakershop.common.extension.setImageUrl
import kotlin.properties.Delegates

typealias OnItemClick<T> = (T) -> Unit

class SneakerListAdapter : RecyclerView.Adapter<SneakerListViewHolder>() {

    var items by Delegates.observable(listOf<SneakerListItemViewModel>()) { props, oldValue, newValue ->
        notifyDataSetChanged()
    }

    var onItemClick: OnItemClick<SneakerListItemViewModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sneaker, parent, false)
        return SneakerListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SneakerListViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount(): Int = items.size

}

class SneakerListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.tvItemSneakerName) lateinit var tvItemSneakerName: TextView
    @BindView(R.id.ivItemSneakerImage) lateinit var ivItemSneakerImage: ImageView
    @BindView(R.id.tvItemSneakerCategory) lateinit var tvItemSneakerCategory: TextView
    @BindView(R.id.tvItemSneakerCollection) lateinit var tvItemSneakerCollection: TextView
    @BindView(R.id.tvItemSneakerPrice) lateinit var tvItemSneakerPrice: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bind(viewModel: SneakerListItemViewModel, onItemClick: OnItemClick<SneakerListItemViewModel>? = null) {
        viewModel.run {
            tvItemSneakerName.text = name
            ivItemSneakerImage.setImageUrl(imageUrl)
            tvItemSneakerCategory.text = categoryName
            tvItemSneakerCollection.text = collectionName
            tvItemSneakerPrice.text = "$ ${price.format()}"
            itemView.setOnClickListener { onItemClick?.invoke(this) }
        }
    }

}