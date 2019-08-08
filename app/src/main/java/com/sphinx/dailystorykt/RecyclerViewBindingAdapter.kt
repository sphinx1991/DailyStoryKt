package com.sphinx.dailystorykt

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewBindingAdapter<T : ViewDataBinding> :
	RecyclerView.Adapter<RecyclerViewBindingAdapter.ViewPlaceHolder<T>>() {

	abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPlaceHolder<T>

	abstract fun onCreateViewBinding(parent: ViewGroup, viewType: Int): T

	abstract fun onBindView(viewBinding: T, position: Int)

	override fun onBindViewHolder(holder: ViewPlaceHolder<T>, position: Int) {
		onBindView(holder.viewDataBinding, position)
		holder.viewDataBinding.executePendingBindings()
	}

	class ViewPlaceHolder<T : ViewDataBinding>(val viewDataBinding: T) :
		RecyclerView.ViewHolder(viewDataBinding.root)
}