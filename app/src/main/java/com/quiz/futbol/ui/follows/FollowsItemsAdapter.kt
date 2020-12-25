package com.quiz.futbol.ui.follows

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.quiz.domain.User
import com.quiz.futbol.R
import com.quiz.futbol.common.inflate
import com.quiz.futbol.utils.Constants.TypeChampionship
import com.quiz.futbol.utils.glideCircleLoadDrawable
import com.quiz.futbol.utils.glideLoadBase64


class FollowsItemsAdapter(private var context: Context,
                          var items: MutableList<User>
) : RecyclerView.Adapter<FollowsItemsAdapter.FollowsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowsViewHolder {
        val view = parent.inflate(R.layout.item_follow, false)
        return FollowsViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FollowsViewHolder, position: Int) {
        val item = items[position]
        holder.textUser.text = item.displayName
        glideLoadBase64(context, item.photoBase64, holder.imageUser)
    }

    class FollowsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textUser: TextView = view.findViewById(R.id.textUser)
        var imageUser: ImageView = view.findViewById(R.id.imageUser)
    }
}