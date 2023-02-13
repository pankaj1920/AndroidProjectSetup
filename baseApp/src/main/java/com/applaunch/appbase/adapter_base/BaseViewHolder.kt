package com.applaunch.appbase.adapter_base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T, VB : ViewDataBinding> :
    RecyclerView.ViewHolder {

    constructor(viewDataBinding: VB) : super(viewDataBinding.root)

    constructor(itemView: View) : super(itemView)

    open var size: Int = 0
    var wholeList: MutableList<T>? = null
    open var lastItemPosition = 0

    var data: T? = null
        set(value) {
            field = value
            data?.let { populateData(it,adapterPosition) }
        }

    fun getPreviousItem(): T? {
        return if (adapterPosition != 0)
            wholeList?.get(adapterPosition - 1)
        else null
    }

    abstract fun populateData(data: T,position:Int)


}