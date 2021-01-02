package com.quiz.futbol.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quiz.domain.Archievements
import com.quiz.futbol.R
import com.quiz.futbol.databinding.ItemProfileEventBinding
import com.quiz.futbol.utils.Constants.STAGE_COMPLETED
import com.quiz.futbol.utils.Constants.TypeChampionship
import com.quiz.futbol.utils.Constants.TypeGame
import com.quiz.futbol.utils.getRelationTime
import com.quiz.futbol.utils.glideCircleLoadBase64
import com.quiz.futbol.utils.glideCircleLoadDrawable


class ProfileBottomSheetItemsAdapter(var items: MutableList<Archievements>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var binding: ItemProfileEventBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserArchievementViewHolder {
        val binding = ItemProfileEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserArchievementViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserArchievementViewHolder) holder.bind(items[position])
    }

    class UserArchievementViewHolder(private val itemBinding: ItemProfileEventBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Archievements) {
            with(itemView.context) {
                glideCircleLoadBase64(this, item.photoBase64, itemBinding.imageUser)
                itemBinding.imageUser.setOnClickListener {
                    item.click()
                }
                itemBinding.layoutUser.setOnClickListener {
                    item.click()
                }
                itemBinding.textPoints.text = item.points.toString()
                itemBinding.textUserName.text = item.displayName
                itemBinding.textTimeArchievement.text = item.userUid
                itemBinding.textTimeArchievement.text = getRelationTime(item.createdAt)
                when(item.typeGame) {
                    TypeGame.BY_NAME.name -> {
                        itemBinding.textArchievement.text = getString(R.string.stadium_by_name)
                        itemBinding.imageArchievement.background = getDrawable(R.drawable.stadium_name)
                    }
                    TypeGame.BY_IMAGE.name -> {
                        itemBinding.textArchievement.text = getString(R.string.stadium_by_image)
                        itemBinding.imageArchievement.setImageResource(R.drawable.stadium_images)
                    }
                    TypeGame.BY_CAPACITY.name -> {
                        itemBinding.textArchievement.text = getString(R.string.stadium_by_capacity)
                        itemBinding.imageArchievement.setImageResource(R.drawable.stadium_capacity)
                    }
                    TypeGame.BY_BUILT.name -> {
                        itemBinding.textArchievement.text = getString(R.string.stadium_by_built)
                        itemBinding.imageArchievement.setImageResource(R.drawable.stadium_built)
                    }
                    STAGE_COMPLETED -> {
                        itemBinding.imageArchievement.setImageResource(R.drawable.ballom)
                        itemBinding.textArchievement.text =  getString(R.string.archieve)
                        itemBinding.layoutEvent.background = getDrawable(R.drawable.gradient_item_stage)
                        when(item.typeChampionship) {
                            TypeChampionship.SPAIN_FIRST_DIVISION.name -> glideCircleLoadDrawable(this, getDrawable(R.drawable.flag_spain)!!, itemBinding.imagePoint)
                            TypeChampionship.ENGLAND_FIRST_DIVISION.name -> glideCircleLoadDrawable(this, getDrawable(R.drawable.flag_united_kingdom)!!, itemBinding.imagePoint)
                            TypeChampionship.ITALY_FIRST_DIVISION.name -> glideCircleLoadDrawable(this, getDrawable(R.drawable.flag_italy)!!, itemBinding.imagePoint)
                            TypeChampionship.GERMAIN_FIRST_DIVISION.name -> glideCircleLoadDrawable(this, getDrawable(R.drawable.flag_germany)!!, itemBinding.imagePoint)
                            TypeChampionship.FRENCH_FIRST_DIVISION.name -> glideCircleLoadDrawable(this, getDrawable(R.drawable.flag_france)!!, itemBinding.imagePoint)
                        }
                    }
                }
            }
        }
    }
}