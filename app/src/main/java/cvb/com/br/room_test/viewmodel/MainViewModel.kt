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
import cvb.com.br.room_test.repository.UserDepartmentJoinRepository
import cvb.com.br.room_test.repository.UserModuleJoinRepository
import cvb.com.br.room_test.repository.UserModuleRepository
import cvb.com.br.room_test.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository,
    private val moduleRepository: ModuleRepository,
    private val departmentRepository: DepartmentRepository,
    private val userModuleRepository: UserModuleRepository,
    private val userDepartmentJoinRepository: UserDepartmentJoinRepository,
    private val userModuleJoinRepository: UserModuleJoinRepository
) : ViewModel() {

    private val mUserList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>>
        get() = mUserList

    private val mModuleList = MutableLiveData<List<Module>>()
    val moduleList: LiveData<List<Module>>
        get() = mModuleList

    private val mDepartmentList = MutableLiveData<List<Department>>()
    val departmentList: LiveData<List<Department>>
        get() = mDepartmentList

    private val mUserModuleList = MutableLiveData<List<UserModule>>()
    val userModuleList: LiveData<List<UserModule>>
        get() = mUserModuleList

    private val mUserDepartmentJoinList = MutableLiveData<List<UserDepartmentJoin>>()
    val userDepartmentJoinList: LiveData<List<UserDepartmentJoin>>
        get() = mUserDepartmentJoinList

    private val mUserModuleJoinList = MutableLiveData<List<UserModuleJoin>>()
    val userModuleJoinList: LiveData<List<UserModuleJoin>>
        get() = mUserModuleJoinList

    fun loadData() {
        viewModelScope.launch {
            mUserList.value = userRepository.getList()

            mModuleList.value = moduleRepository.getList()

            mDepartmentList.value = departmentRepository.getList()

            mUserModuleList.value = userModuleRepository.getList()

            mUserDepartmentJoinList.value = userDepartmentJoinRepository.getList()

            mUserModuleJoinList.value = userModuleJoinRepository.getList()
        }
    }
}