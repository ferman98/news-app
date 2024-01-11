package co.ferman.saltweather.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.ferman.saltweather.databinding.ActivityDetailBinding
import co.ferman.saltweather.util.data.model.ArticlesItem
import co.ferman.saltweather.util.extension.setImage
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding: ActivityDetailBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setLayout()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun setLayout() {
        val extra = intent.getStringExtra(EXTRA_DATA)
        val objExtra = Gson().fromJson(extra, ArticlesItem::class.java)
        binding.apply {
            imgBack.setOnClickListener {
                intent.removeExtra(EXTRA_DATA)
                finish()
            }
            objExtra.urlToImage?.let { imgMain.setImage(it) }
            tvTitle.text = objExtra.title
            tvDesc.text = objExtra.content
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}