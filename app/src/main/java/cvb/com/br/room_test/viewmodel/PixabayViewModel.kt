package cvb.com.br.room_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cvb.com.br.room_test.db.data.UserDepartmentJoin
import cvb.com.br.room_test.db.data.UserModuleJoin
import cvb.com.br.room_test.db.entity.Department
import cvb.com.br.room_test.db.entity.Module
import cvb.com.br.room_test.db.entity.User
import cvb.com.br.room_test.db.entity.UserModule
import cvb.com.br.room_test.repository.DepartmentRepository
import cvb.com.br.room_test.repository.ModuleRepository
import cvb.com.br.room_test.repository.PixabayRepository
import cvb.com.br.room_test.repository.UserDepartmentJoinRepository
import cvb.com.br.room_test.repository.UserModuleJoinRepository
import cvb.com.br.room_test.repository.UserModuleRepository
import cvb.com.br.room_test.repository.UserRepository
import cvb.com.br.room_test.viewmodel.sealed.LoadPixabayStatus
import cvb.com.br.room_test.viewmodel.sealed.LoadUserStatus
import cvb.com.br.room_test.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PixabayViewModel @Inject constructor(private val pixabayRepository: PixabayRepository) : ViewModel() {

    private val singleToastEvent = Event("1")

    private val _showSingleToast = MutableLiveData<Event<String>>()
    val showSingleToast : LiveData<Event<String>>
        get() = _showSingleToast


    fun triggerSingleEvent() {
        _showSingleToast.value = singleToastEvent
    }

    //=========

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast : LiveData<Event<String>>
        get() = _showToast

    fun triggerEvent() {
        _showToast.value = Event("1")  // Trigger the event by setting a new Event as a new value
    }

    //=========

    private val mLoadPixabayStatus = MutableLiveData<LoadPixabayStatus>()
    val loadPixabayStatus: LiveData<LoadPixabayStatus>
        get() = mLoadPixabayStatus

    fun loadPixabay() {
        viewModelScope.launch {
            try {
                mLoadPixabayStatus.value = LoadPixabayStatus.Loading

                val userList = pixabayRepository.get()

                mLoadPixabayStatus.value = LoadPixabayStatus.Success(userList)
            } catch (error: Throwable) {
                mLoadPixabayStatus.value = LoadPixabayStatus.Error(error)
            }
        }
    }
}