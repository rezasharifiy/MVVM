package com.example.mvvm_artichecture_sample.ui.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_artichecture_sample.R
import com.example.mvvm_artichecture_sample.data.remote.apimodel.Country
import java.util.*

class MainAdapter(private val mContext: Context, private val hanlder: MainAdapterHanlder) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(), View.OnClickListener {
    private val mList: MutableList<Country>?

    init {
        mList = ArrayList()
    }

    fun addItem(list: List<Country>) {
        mList!!.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.main_item_list, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val model = mList!![position]
        holder.title.text = model.name
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun onClick(v: View) {
        val holder = v.tag as MainViewHolder
        val pos = holder.layoutPosition
        clicked(pos)
    }

    private fun clicked(position: Int) {
        hanlder.onItemClick(position)
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView

        init {
            title = itemView.findViewById(R.id.title)

            itemView.tag = this
            itemView.setOnClickListener(this@MainAdapter)
        }
    }


}
