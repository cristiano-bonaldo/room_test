package cvb.com.br.room_test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cvb.com.br.room_test.repository.DepartmentRepository
import cvb.com.br.room_test.repository.ModuleRepository
import cvb.com.br.room_test.repository.UserDepartmentJoinRepository
import cvb.com.br.room_test.repository.UserModuleJoinRepository
import cvb.com.br.room_test.repository.UserModuleRepository
import cvb.com.br.room_test.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val userRepository: UserRepository,
    private val moduleRepository: ModuleRepository,
    private val departmentRepository: DepartmentRepository,
    private val userModuleRepository: UserModuleRepository,
    private val userDepartmentJoinRepository: UserDepartmentJoinRepository,
    private val userModuleJoinRepository: UserModuleJoinRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (MainViewModel(
            userRepository,
            moduleRepository,
            departmentRepository,
            userModuleRepository,
            userDepartmentJoinRepository,
            userModuleJoinRepository
        ) as T)
}