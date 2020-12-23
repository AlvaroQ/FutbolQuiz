package com.quiz.futbol.ui.profile

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.quiz.futbol.R
import com.quiz.futbol.common.inflate
import com.quiz.futbol.utils.Constants.TypeChampionship
import com.quiz.futbol.utils.glideCircleLoadDrawable


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
                TypeChampionship.SPAIN_FIRST_DIVISION.name -> R.drawable.flag_spain
                TypeChampionship.ENGLAND_FIRST_DIVISION.name -> R.drawable.flag_united_kingdom
                TypeChampionship.ITALY_FIRST_DIVISION.name -> R.drawable.flag_italy
                TypeChampionship.GERMAIN_FIRST_DIVISION.name -> R.drawable.flag_germany
                TypeChampionship.FRENCH_FIRST_DIVISION.name -> R.drawable.flag_france
                else -> { R.drawable.flag_brazil } })!!, holder.itemRibbon)
    }

    class ArchievementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemRibbon: ImageView = view.findViewById(R.id.itemRibbon)
    }
}