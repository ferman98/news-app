package co.ferman.saltweather.util.component

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import co.ferman.saltweather.R

class RelativeLayoutCircle @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : RelativeLayout(
    context,
    attributeSet,
    defStyleAttr,
    defStyleRes
) {

    private val attribute =
        context.obtainStyledAttributes(attributeSet, R.styleable.RelativeLayoutCircle)

    private var radius: Float =
        attribute.getDimension(R.styleable.RelativeLayoutCircle_android_radius, 0f)
    private var borderWidth: Float =
        attribute.getDimension(R.styleable.RelativeLayoutCircle_cvBorderWidth, 0f)
    private var borderColor: ColorStateList? =
        attribute.getColorStateList((R.styleable.RelativeLayoutCircle_cvBorderColor))
    private var backgroundColor: ColorStateList =
        attribute.getColorStateList(R.styleable.RelativeLayoutCircle_cvBackgroundColor)
            ?: ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white))
    private var cvAlpha: Int = attribute.getInt(R.styleable.RelativeLayoutCircle_cvAlpha, 255)

    init {
        clipToOutline = true
        applyBackground()
        attribute.recycle()
    }

    private fun applyBackground() {
        val gd = GradientDrawable()
        if (radius == 0f) gd.shape = GradientDrawable.OVAL
        if (radius > 0) gd.cornerRadius = radius
        if (borderColor != null && borderWidth != 0f) {
            gd.setStroke(borderWidth.toInt(), borderColor)
        }
        gd.color = backgroundColor
        gd.alpha = cvAlpha
        background = gd
    }
}