package co.ferman.saltweather.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String) {
    if (url.isEmpty()) return
    Glide.with(context).load(url).into(this)
}