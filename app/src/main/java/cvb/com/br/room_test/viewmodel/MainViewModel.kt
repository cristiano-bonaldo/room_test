package cvb.com.br.room_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cvb.com.br.room_test.db.entity.User
import cvb.com.br.room_test.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val mUserList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>>
        get() = mUserList

    fun loadUser() {
        viewModelScope.launch {
            mUserList.value = userRepository.getList()
        }
    }



}