package com.quiz.futbol.ui.select

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.quiz.futbol.R
import com.quiz.futbol.common.inflate
import com.quiz.futbol.utils.Constants.TypeChampionship
import com.quiz.futbol.utils.Constants.TypeChampionship.*
import com.quiz.futbol.utils.Constants.TypeGame
import com.quiz.futbol.utils.Constants.TypeGame.*
import com.quiz.futbol.utils.setSafeOnClickListener


class SelectItemsAdapter(private var context: Context,
                         var items: MutableList<SelectItem>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val view = parent.inflate(R.layout.item_select_header, false)
            HeaderViewHolder(view)
        } else {
            val view = parent.inflate(R.layout.item_select_type_game, false)
            BodyViewHolder(view)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return if (items[position].headerType == null) {
            TYPE_BODY
        } else {
            TYPE_HEADER
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if(getItemViewType(position) == TYPE_HEADER) {
            (holder as HeaderViewHolder).textChampionship.text = item.title
            holder.imageChampionship.background = ContextCompat.getDrawable(context,
                    when (item.headerType) {
                        SPAIN_FIRST_DIVISION -> R.drawable.flag_spain
                        SPAIN_SECOND_DIVISION -> R.drawable.flag_spain
                        ENGLAND_FIRST_DIVISION -> R.drawable.flag_united_kingdom
                        ENGLAND_SECOND_DIVISION-> R.drawable.flag_united_kingdom
                        ITALY_FIRST_DIVISION -> R.drawable.flag_italy
                        ITALY_SECOND_DIVISION-> R.drawable.flag_italy
                        else -> { R.drawable.flag_germany }
                    })
        } else {
            (holder as BodyViewHolder).nameTypeGame.text = item.title
            holder.blockedLayout.visibility = if (item.isBlocked) View.VISIBLE else View.GONE
            holder.imageStadium.background = ContextCompat.getDrawable(context,
                    when (item.type) {
                        BY_NAME -> R.drawable.stadium_name
                        BY_IMAGE -> R.drawable.stadium_images
                        BY_BUILT -> R.drawable.stadium_built
                        else -> R.drawable.stadium_capacity
                    })
            holder.itemView.setSafeOnClickListener { item.click() }
        }
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageChampionship: ImageView = view.findViewById(R.id.imageChampionship)
        var textChampionship: TextView = view.findViewById(R.id.textChampionship)
    }

    class BodyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameTypeGame: TextView = view.findViewById(R.id.nameTypeGame)
        var imageStadium: ImageView = view.findViewById(R.id.imageStadium)
        var blockedLayout: ConstraintLayout = view.findViewById(R.id.blockedLayout)
    }

    companion object {
        const val TYPE_HEADER = 1
        const val TYPE_BODY = 2
    }
}
data class SelectItem(
        val title: String,
        val isBlocked: Boolean,
        val type: TypeGame?,
        val headerType: TypeChampionship? = null,
        val click: () -> Unit)