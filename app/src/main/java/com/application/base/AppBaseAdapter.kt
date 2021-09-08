package com.application.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class AppBaseAdapter<M, VB : ViewBinding> : RecyclerView.Adapter<AppBaseAdapter<M, VB>.ViewHolder>() {

    val list = ArrayList<M>()

    private var mOnFilterBind: OnFilterBind<M>? = null
    private var mOnLayoutSelector: OnLayoutSelector? = null

    private lateinit var mRecyclerView: RecyclerView
    private var emptyView: View? = null

    private var onClickView: ((View, Int, M) -> Unit)? = null
    private var onCreateViewHolderBlock: ((View) -> View)? = null

    private var layout: Int = 0
    lateinit var binding: VB

    abstract fun getViewBinding(parent: ViewGroup, attachMoRoot: Boolean): VB

    fun setOnLayoutSelector(mOnLayoutSelector: OnLayoutSelector) {
        this.mOnLayoutSelector = mOnLayoutSelector
    }

    interface OnFilterBind<in M> {
        fun onFilter(searchKey: String): ArrayList<in M>
    }

    fun setItemClickListener(onClickView: (View, Int, M) -> Unit) {
        this.onClickView = onClickView
    }

    fun setOnCreateViewHolderBlock(block: ((View) -> View)) {
        this.onCreateViewHolderBlock = block
    }

    interface OnLayoutSelector {
        fun selectLayout(itemViewMype: Int): Int?
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mOnLayoutSelector != null) {
            if (mOnLayoutSelector?.selectLayout(viewType) != null) {
                layout = mOnLayoutSelector?.selectLayout(viewType)!!
            }
        }

        binding = getViewBinding(parent, false)
        var v = binding.root
        onCreateViewHolderBlock?.let { it -> v = it(v) }
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            onBind(holder.itemViewType, holder.view, position, list[position], payloads)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBind(holder.itemViewType, holder.view, position, list[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val view: VB, val context: Context) : RecyclerView.ViewHolder(view.root) {

        init {
            setClickableView(view.root).forEach { clickView ->
                clickView?.setOnClickListener {
                    onClickView?.let { it1 -> it1(it, adapterPosition, list[adapterPosition]) }
                }
            }
        }

        fun getBindView(): View = view.root
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    open fun addItemAt(index: Int, item: M) {
        list.add(index, item)
        notifyItemInserted(index)
    }

    open fun addItem(item: M) {
        list.add(item)
        notifyItemInserted(list.size)
    }

    open fun addAll(dataList: Collection<M>) {
        list.clear()
        list.addAll(dataList)
        notifyDataSetChanged()
        if (::mRecyclerView.isInitialized) mRecyclerView.checkIfEmpty(emptyView)
    }

    open fun getItem(position: Int): M {
        return list[position]
    }

    open fun appendAll(dataList: Collection<M>) {
        val oldSize = list.size
        list.addAll(dataList)
        notifyItemRangeInserted(oldSize, dataList.size)
    }

    open fun clearAll() {
        list.clear()
        notifyDataSetChanged()
    }

    open fun removeItemAt(position: Int) {
        if (position in list.indices) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun setEmptyView(emptyView: View?) {
        this.emptyView = emptyView
    }

    abstract fun setClickableView(itemView: View): List<View?>

    abstract fun onBind(
        viewType: Int,
        view: VB,
        position: Int,
        item: M,
        payloads: MutableList<Any>? = null
    )

    open fun selectMyLayout(itemViewType: Int): Int? = null

    private fun RecyclerView.checkIfEmpty(emptyView: View?) {
        if (emptyView != null && adapter != null) {
            val emptyViewVisible = adapter?.itemCount == 0
            emptyView.visibility = if (emptyViewVisible) View.VISIBLE else View.GONE
            visibility = if (emptyViewVisible) View.GONE else View.VISIBLE
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}