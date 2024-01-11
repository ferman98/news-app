package co.ferman.saltweather.ui.home.view.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import co.ferman.saltweather.util.data.api.NetworkServices
import co.ferman.saltweather.util.data.model.APIStatus
import co.ferman.saltweather.util.data.di.NewsModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsViewModel @Inject constructor(private var services: NetworkServices) : ViewModel() {

    @Inject
    lateinit var newsRepo: NewsModule

    fun getNews(): LiveData<APIStatus> {
        return flow {
            emit(APIStatus.Loading)

            val response = services.getNews()
            if (response.isSuccessful) {
                emit(APIStatus.Success(response.body()))
            } else {
                emit(APIStatus.Error(404))
            }
        }
            .flowOn(Dispatchers.IO)
            .asLiveData()
    }
}