package cvb.com.br.room_test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cvb.com.br.room_test.repository.PixabayRepository

@Suppress("UNCHECKED_CAST")
class PixabayViewModelFactory(private val pixabayRepository: PixabayRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (PixabayViewModel(pixabayRepository) as T)
}