package cvb.com.br.room_test.viewmodel.sealed

import cvb.com.br.room_test.network.model.PixabayResult

sealed class LoadPixabayStatus {
    data object Loading : LoadPixabayStatus()
    class Error(val error: Throwable) : LoadPixabayStatus()
    class Success(val pixabayResult: PixabayResult) : LoadPixabayStatus()
}
