package co.ferman.saltweather.ui.home.view.news

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.ferman.saltweather.R
import co.ferman.saltweather.util.data.model.APIStatus
import co.ferman.saltweather.util.data.model.NewsTopHeadlines
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : RecyclerView(
    context,
    attributeSet,
    defStyleAttr,
), LifecycleOwner, ViewModelStoreOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)
    override val lifecycle: Lifecycle = lifecycleRegistry
    override val viewModelStore: ViewModelStore = ViewModelStore()

    @Inject
    lateinit var vmGeneric: NewsViewModelFactory

    private val vm: NewsViewModel by lazy {
        ViewModelProvider(this, vmGeneric)[NewsViewModel::class.java]
    }

    private fun adapter() = adapter as NewsAdapter

    init {
        layoutManager = LinearLayoutManager(context, VERTICAL, false)
        overScrollMode = OVER_SCROLL_NEVER
        addItemDecoration(Decoration(context))
        setLayout()
        if (isInEditMode.not()) getNews()
    }

    private fun setLayout() {
        adapter = NewsAdapter()
    }

    private fun getNews() {
        vm.getNews().observe(this) {
            when (it) {
                APIStatus.Loading -> {}
                is APIStatus.Error -> {}
                is APIStatus.Success<*> -> {
                    (it.data as? NewsTopHeadlines)
                        ?.articles
                        ?.filter { a -> a.urlToImage != null }
                        ?.also { articles -> adapter().update(articles) }
                }
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    private class Decoration(private val context: Context) : ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
            val horizontalGap = context.resources.getDimensionPixelOffset(R.dimen.container)
            val verticalGap = context.resources.getDimensionPixelOffset(R.dimen.x2)
            outRect.left = horizontalGap
            outRect.right = horizontalGap
            outRect.top = verticalGap
            outRect.bottom = verticalGap
        }
    }
}