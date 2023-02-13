package com.applaunch.appbase.adapter_base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder<T, *>,>(var data: MutableList<T>?) :
    RecyclerView.Adapter<VH>() {

    fun inflateDataBinding(layout: Int, parent: ViewGroup): ViewDataBinding? {
        return DataBindingUtil.bind(
            LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.size = itemCount
        holder.wholeList = data
        holder.lastItemPosition = itemCount - 1
        holder.data = getItem(position)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    @Throws(IndexOutOfBoundsException::class)
    fun getItem(position: Int): T? {
        return data?.get(position)
    }

    var onItemClick: ((position: Int, item:Any, type:String) -> Unit)? = null
    var onSelectItemClick: ((position: Int,prevPosition:Int, item:Any, type:String) -> Unit)? = null
}

