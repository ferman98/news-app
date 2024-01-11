package co.ferman.saltweather.ui.home.view.headlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import co.ferman.saltweather.util.data.api.NetworkServices
import co.ferman.saltweather.util.data.model.APIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(private val services: NetworkServices) : ViewModel() {

    fun getHighlight(): LiveData<APIStatus> {
        return flow {
            emit(APIStatus.Loading)

            val response = services.getNewsTopHeadlines()
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