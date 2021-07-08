package com.mubin.quranapp.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mubin.quranapp.R
import com.mubin.quranapp.adapter.MainItemAdapter.MainViewHolder
import com.mubin.quranapp.databinding.ItemArbiListBinding
import com.mubin.quranapp.model.Result
import com.mubin.quranapp.model.Translation
import java.util.*

class MainItemAdapter(private val context: Context, private val results: List<Result>) :
    RecyclerView.Adapter<MainViewHolder>() {
    private val translations: MutableList<Translation> = ArrayList()
    private var adapter: SubItemsAdapter? = null
    private var layoutInflater: LayoutInflater? = null
    var lastPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val itemArbiListBinding: ItemArbiListBinding = DataBindingUtil.inflate(
            layoutInflater!!, R.layout.item_arbi_list, parent, false
        )
        return MainViewHolder(itemArbiListBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindResult(results[position])
        if (holder.adapterPosition > lastPosition) {
            val animation = AnimationUtils.loadAnimation(
                context, R.anim.slide_in
            )
            holder.itemView.startAnimation(animation)
            lastPosition = holder.adapterPosition
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    inner class MainViewHolder(private val itemArbiListBinding: ItemArbiListBinding) :
        RecyclerView.ViewHolder(
            itemArbiListBinding.root
        ) {
        fun bindResult(result: Result) {
            translations.clear()
            translations.addAll(result.getTranslations())
            adapter = SubItemsAdapter(context, translations)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                itemArbiListBinding.arbiText.text =
                    Html.fromHtml(result.text, Html.FROM_HTML_MODE_COMPACT)
            } else {
                itemArbiListBinding.arbiText.text = Html.fromHtml(result.text)
            }
            itemArbiListBinding.translationRV.adapter = adapter
        }
    }
}

