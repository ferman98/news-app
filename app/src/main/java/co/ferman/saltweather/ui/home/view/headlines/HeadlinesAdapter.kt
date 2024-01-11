package co.ferman.saltweather.ui.home.view.headlines

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import co.ferman.saltweather.R
import co.ferman.saltweather.ui.detail.DetailActivity
import co.ferman.saltweather.util.component.RelativeLayoutCircle
import co.ferman.saltweather.util.data.model.ArticlesItem
import co.ferman.saltweather.util.extension.setImage
import com.google.gson.Gson

class HeadlinesAdapter : RecyclerView.Adapter<HeadlinesAdapter.VH>() {

    private lateinit var context: Context
    private var datas: List<ArticlesItem> = listOf()

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val root = v.rootView as RelativeLayoutCircle
        val img: ImageView = v.findViewById(R.id.imgHeadline)
    }

    fun update(data: List<ArticlesItem>) {
        datas = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        context = parent.context
        return VH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.headlines_adapter, parent, false)
        )
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        datas[position].urlToImage?.let { holder.img.setImage(it) }
        holder.root.setOnClickListener {
            (context as? Activity)?.apply {
                val i = Intent(this, DetailActivity::class.java)
                i.putExtra(DetailActivity.EXTRA_DATA, Gson().toJson(datas[position]))
                startActivity(i)
            }
        }
    }
}