package com.mubin.quranapp.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mubin.quranapp.R
import com.mubin.quranapp.adapter.SubItemsAdapter.SubItemsViewHolder
import com.mubin.quranapp.databinding.ItemTranslateListBinding
import com.mubin.quranapp.model.Translation

class SubItemsAdapter(private val context: Context, private val translations: List<Translation>) :
    RecyclerView.Adapter<SubItemsViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubItemsViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val itemTranslateListBinding: ItemTranslateListBinding = DataBindingUtil.inflate(
            layoutInflater!!, R.layout.item_translate_list, parent, false
        )
        return SubItemsViewHolder(itemTranslateListBinding)
    }

    override fun onBindViewHolder(holder: SubItemsViewHolder, position: Int) {
        holder.bindTranslation(translations[position])
    }

    override fun getItemCount(): Int {
        return translations.size
    }

    inner class SubItemsViewHolder(private val itemTranslateListBinding: ItemTranslateListBinding) :
        RecyclerView.ViewHolder(
            itemTranslateListBinding.root
        ) {
        fun bindTranslation(translation: Translation) {
            itemTranslateListBinding.nameWriter.text = translation.name
            //            itemTranslateListBinding.translateTV.setText(translation.getText());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                itemTranslateListBinding.translateTV.text =
                    Html.fromHtml(translation.text, Html.FROM_HTML_MODE_COMPACT)
            } else {
                itemTranslateListBinding.translateTV.text = Html.fromHtml(translation.text)
            }
        }
    }
}