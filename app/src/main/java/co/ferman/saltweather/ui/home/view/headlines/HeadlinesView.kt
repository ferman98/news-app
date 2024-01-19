package co.ferman.saltweather.ui.home.view.headlines

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
import co.ferman.saltweather.util.extension.dp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HeadlinesView @JvmOverloads constructor(
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
    lateinit var vmGeneric: HeadlinesViewModelFactory

    private val vm: HeadlinesViewModel by lazy {
        ViewModelProvider(this, vmGeneric)[HeadlinesViewModel::class.java]
    }

    private fun adapter() = adapter as HeadlinesAdapter

    init {
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        overScrollMode = OVER_SCROLL_NEVER
        addItemDecoration(ItemDecorationHorizontal(context))
        setLayout()
        if (isInEditMode.not()) getHighlight()
    }

    private fun setLayout() {
        adapter = HeadlinesAdapter()
    }

    private fun getHighlight() {
        vm.getHighlight().observe(this) {
            when (it) {
                APIStatus.Loading -> {}
                is APIStatus.Error -> {}
                is APIStatus.Success<NewsTopHeadlines> -> {
                    it.data
                        .articles
                        .filter { a -> a.urlToImage != null }
                        .also { articles -> adapter().update(articles) }
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

    private class ItemDecorationHorizontal(private val context: Context) : ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
            val gap = 8.dp()
            when (parent.getChildLayoutPosition(view)) {
                0 -> outRect.left = context.resources.getDimensionPixelOffset(R.dimen.container)
                state.itemCount - 1 -> {
                    outRect.left = gap
                    outRect.right = context.resources.getDimensionPixelOffset(R.dimen.container)
                }

                else -> outRect.left = gap
            }
        }
    }
}