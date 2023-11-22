package com.application.base

import android.widget.Filter
import android.widget.Filterable
import androidx.viewbinding.ViewBinding

// M | VB => Template types
abstract class AppBaseFilterAdapter<M, VB : ViewBinding>() : AppBaseAdapter<M, VB>(), Filterable {

    val mainList = ArrayList<M>()

    abstract fun includeItem(query: CharSequence?, item: M): Boolean

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.isNullOrEmpty()) {
                    val filterResults = FilterResults()
                    filterResults.values = mainList
                    filterResults.count = mainList.size
                    return filterResults
                }

                val tempList = ArrayList<M>()
                mainList.forEach {
                    val isExist = includeItem(constraint, it)
                    if (isExist) {
                        tempList.add(it)
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = tempList
                filterResults.count = tempList.size

                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                list.clear()
                list.addAll(results.values as ArrayList<M>)
                notifyDataSetChanged()
            }
        }
    }

    override fun addAll(dataList: Collection<M>) {
        super.addAll(dataList)
        mainList.clear()
        mainList.addAll(dataList)
    }
}