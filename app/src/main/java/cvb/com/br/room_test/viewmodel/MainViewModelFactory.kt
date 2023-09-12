package cvb.com.br.room_test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cvb.com.br.room_test.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory (private val userRepository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (MainViewModel(userRepository) as T)
}