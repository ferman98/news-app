package co.ferman.saltweather.ui.home.view.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.ferman.saltweather.util.data.api.NetworkServices
import javax.inject.Inject


class NewsViewModelFactory @Inject constructor(
    private var services: NetworkServices
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(services) as T
    }
}