package co.ferman.saltweather.ui.home.view.headlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ferman.saltweather.util.data.api.NetworkServices
import co.ferman.saltweather.util.data.model.APIStatus
import co.ferman.saltweather.util.data.model.NewsTopHeadlines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(private val services: NetworkServices) : ViewModel() {
    private val headlinesLiveData: MutableLiveData<APIStatus<NewsTopHeadlines>> =
        MutableLiveData(APIStatus.Loading)

    fun getHighlight(): LiveData<APIStatus<NewsTopHeadlines>> {
        viewModelScope.launch {
            val response = services.getNewsTopHeadlines()
            if (response.isSuccessful) {
                headlinesLiveData.value = APIStatus.Success(response.body() ?: NewsTopHeadlines())
            } else {
                headlinesLiveData.value = APIStatus.Error(404)
            }
        }
        return headlinesLiveData
    }
}