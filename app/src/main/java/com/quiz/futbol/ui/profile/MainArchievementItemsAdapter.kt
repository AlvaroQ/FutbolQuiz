package com.quiz.futbol.ui.profile

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.quiz.futbol.R
import com.quiz.futbol.common.inflate
import com.quiz.futbol.utils.Constants.SPAIN
import com.quiz.futbol.utils.Constants.ENGLAND
import com.quiz.futbol.utils.Constants.ITALY
import com.quiz.futbol.utils.Constants.GERMANY
import com.quiz.futbol.utils.Constants.FRANCE
import com.quiz.futbol.utils.Constants.BRAZIL
import com.quiz.futbol.utils.Constants.ARGENTINA
import com.quiz.futbol.utils.glideCircleLoadDrawable
import java.util.*


class MainArchievementItemsAdapter(private var context: Context,
                                   var items: MutableList<String>
) : RecyclerView.Adapter<MainArchievementItemsAdapter.ArchievementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchievementViewHolder {
        val view = parent.inflate(R.layout.item_profile_achievements, false)
        return ArchievementViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ArchievementViewHolder, position: Int) {
        val item = items[position]

        glideCircleLoadDrawable(context, ContextCompat.getDrawable(context,
            when (item) {
                SPAIN -> R.drawable.flag_spain
                ENGLAND -> R.drawable.flag_united_kingdom
                ITALY -> R.drawable.flag_italy
                GERMANY -> R.drawable.flag_germany
                FRANCE -> R.drawable.flag_france
                BRAZIL -> R.drawable.flag_brazil
                ARGENTINA -> R.drawable.flag_argentina
                else -> { R.drawable.flag_brazil } })!!, holder.itemRibbon)
    }

    class ArchievementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemRibbon: ImageView = view.findViewById(R.id.itemRibbon)
    }
}