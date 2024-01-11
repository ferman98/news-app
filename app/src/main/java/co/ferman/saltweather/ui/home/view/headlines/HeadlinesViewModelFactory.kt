package co.ferman.saltweather.ui.home.view.headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.ferman.saltweather.util.data.api.NetworkServices
import javax.inject.Inject

class HeadlinesViewModelFactory @Inject constructor(
    private var services: NetworkServices
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HeadlinesViewModel(services) as T
    }
}