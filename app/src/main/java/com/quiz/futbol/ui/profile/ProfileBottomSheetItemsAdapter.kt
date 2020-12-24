package com.quiz.futbol.ui.profile

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.quiz.domain.Archievements
import com.quiz.futbol.R
import com.quiz.futbol.common.inflate
import com.quiz.futbol.utils.Constants.STAGE_COMPLETED
import com.quiz.futbol.utils.Constants.TypeChampionship
import com.quiz.futbol.utils.Constants.TypeGame
import com.quiz.futbol.utils.getRelationTime
import com.quiz.futbol.utils.glideCircleLoadBase64
import com.quiz.futbol.utils.glideCircleLoadDrawable


class ProfileBottomSheetItemsAdapter(private var context: Context,
                                   var items: MutableList<Archievements>
) : RecyclerView.Adapter<ProfileBottomSheetItemsAdapter.UserArchievementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserArchievementViewHolder {
        val view = parent.inflate(R.layout.item_profile_event, false)
        return UserArchievementViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserArchievementViewHolder, position: Int) {
        val item = items[position]

        glideCircleLoadBase64(context, item.photoBase64, holder.imageUser)
        holder.textPoints.text = item.points.toString()
        holder.textUserName.text = item.displayName
        holder.textTimeArchievement.text = item.userUid
        holder.textTimeArchievement.text = context.getRelationTime(item.createdAt)
        when(item.typeGame) {
            TypeGame.BY_NAME.name -> {
                holder.textArchievement.text = context.getString(R.string.stadium_by_name)
                holder.imageArchievement.background = context.getDrawable(R.drawable.stadium_name)
            }
            TypeGame.BY_IMAGE.name -> {
                holder.textArchievement.text = context.getString(R.string.stadium_by_image)
                holder.imageArchievement.setImageResource(R.drawable.stadium_images)
            }
            TypeGame.BY_CAPACITY.name -> {
                holder.textArchievement.text = context.getString(R.string.stadium_by_capacity)
                holder.imageArchievement.setImageResource(R.drawable.stadium_capacity)
            }
            TypeGame.BY_BUILT.name -> {
                holder.textArchievement.text = context.getString(R.string.stadium_by_built)
                holder.imageArchievement.setImageResource(R.drawable.stadium_built)
            }
            STAGE_COMPLETED -> {
                holder.imageArchievement.setImageResource(R.drawable.ballom)
                holder.textArchievement.text =  context.getString(R.string.archieve)
                holder.layoutEvent.background = context.getDrawable(R.drawable.gradient_item_stage)
                when(item.typeChampionship) {
                    TypeChampionship.SPAIN_FIRST_DIVISION.name -> glideCircleLoadDrawable(context, context.getDrawable(R.drawable.flag_spain)!!, holder.imagePoint)
                    TypeChampionship.ENGLAND_FIRST_DIVISION.name -> glideCircleLoadDrawable(context, context.getDrawable(R.drawable.flag_united_kingdom)!!, holder.imagePoint)
                    TypeChampionship.ITALY_FIRST_DIVISION.name -> glideCircleLoadDrawable(context, context.getDrawable(R.drawable.flag_italy)!!, holder.imagePoint)
                    TypeChampionship.GERMAIN_FIRST_DIVISION.name -> glideCircleLoadDrawable(context, context.getDrawable(R.drawable.flag_germany)!!, holder.imagePoint)
                    TypeChampionship.FRENCH_FIRST_DIVISION.name -> glideCircleLoadDrawable(context, context.getDrawable(R.drawable.flag_france)!!, holder.imagePoint)
                }
            }
        }
    }

    class UserArchievementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imagePoint: ImageView = view.findViewById(R.id.imagePoint)
        var imageArchievement: ImageView = view.findViewById(R.id.imageArchievement)
        var layoutEvent: ConstraintLayout = view.findViewById(R.id.layoutEvent)

        var imageUser: ImageView = view.findViewById(R.id.imageUser)
        var textUserName: TextView = view.findViewById(R.id.textUserName)
        var textPoints: TextView = view.findViewById(R.id.textPoints)
        var textTimeArchievement: TextView = view.findViewById(R.id.textTimeArchievement)

        var textArchievement: TextView = view.findViewById(R.id.textArchievement)
    }
}