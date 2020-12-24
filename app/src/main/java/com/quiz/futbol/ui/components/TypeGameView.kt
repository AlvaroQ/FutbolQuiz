package com.quiz.futbol.ui.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.quiz.futbol.R

class TypeGameView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var isBlocked: Boolean
    private var title: String
    private var stadium: Drawable

    private val nameTypeGame: TextView
    private val blockedLayout: ConstraintLayout
    private val imageStadium: ImageView

    init {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.item_select_type_game, this, true)

        nameTypeGame = view.findViewById(R.id.nameTypeGame)
        imageStadium = view.findViewById(R.id.imageStadium)
        blockedLayout = view.findViewById(R.id.blockedLayout)


        val a = context.obtainStyledAttributes(attrs, R.styleable.TypeGameView)
        isBlocked = a.getBoolean(R.styleable.TypeGameView_isBlocked, true)
        title = a.getString(R.styleable.TypeGameView_title)!!
        stadium = a.getDrawable(R.styleable.TypeGameView_stadium)!!
        a.recycle()


        nameTypeGame.text = title
        if(!isBlocked) blockedLayout.visibility = View.GONE
        imageStadium.background = stadium
    }

}