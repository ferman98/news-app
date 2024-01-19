package co.ferman.saltweather.ui.home.view.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ferman.saltweather.util.data.api.NetworkServices
import co.ferman.saltweather.util.data.model.APIStatus
import co.ferman.saltweather.util.data.model.NewsTopHeadlines
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private var services: NetworkServices) : ViewModel() {
    private val newsLiveData: MutableLiveData<APIStatus<NewsTopHeadlines>> =
        MutableLiveData(APIStatus.Loading)

    fun getNews(): LiveData<APIStatus<NewsTopHeadlines>> {
        viewModelScope.launch {
            val response = services.getNews()
            if (response.isSuccessful) {
                newsLiveData.value = APIStatus.Success(response.body() ?: NewsTopHeadlines())
            } else {
                newsLiveData.value = APIStatus.Error(404)
            }
        }
        return newsLiveData
    }
}