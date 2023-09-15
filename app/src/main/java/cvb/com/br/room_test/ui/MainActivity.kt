package cvb.com.br.room_test.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cvb.com.br.room_test.R
import cvb.com.br.room_test.data.LocalDepartmentDataSource
import cvb.com.br.room_test.data.LocalModuleDataSource
import cvb.com.br.room_test.data.LocalUserDataSource
import cvb.com.br.room_test.data.LocalUserDepartmentJoinDataSource
import cvb.com.br.room_test.data.LocalUserModuleDataSource
import cvb.com.br.room_test.data.LocalUserModuleJoinDataSource
import cvb.com.br.room_test.repository.DepartmentRepository
import cvb.com.br.room_test.repository.ModuleRepository
import cvb.com.br.room_test.repository.UserDepartmentJoinRepository
import cvb.com.br.room_test.repository.UserModuleJoinRepository
import cvb.com.br.room_test.repository.UserModuleRepository
import cvb.com.br.room_test.repository.UserRepository
import cvb.com.br.room_test.viewmodel.MainViewModel
import cvb.com.br.room_test.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        val userDataSource = LocalUserDataSource(applicationContext)
        val userRepository = UserRepository(userDataSource)

        val moduleDataSource = LocalModuleDataSource(applicationContext)
        val moduleRepository = ModuleRepository(moduleDataSource)

        val departmentDataSource = LocalDepartmentDataSource(applicationContext)
        val departmentRepository = DepartmentRepository(departmentDataSource)

        val userModuleDataSource = LocalUserModuleDataSource(applicationContext)
        val userModuleRepository = UserModuleRepository(userModuleDataSource)

        val userDepartmentDataSource = LocalUserDepartmentJoinDataSource(applicationContext)
        val userDepartmentRepository = UserDepartmentJoinRepository(userDepartmentDataSource)

        val userModuleJoinDataSource = LocalUserModuleJoinDataSource(applicationContext)
        val userModuleJoinRepository = UserModuleJoinRepository(userModuleJoinDataSource)

        MainViewModelFactory(
            userRepository,
            moduleRepository,
            departmentRepository,
            userModuleRepository,
            userDepartmentRepository,
            userModuleJoinRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.moduleList.observe(this) { moduleList ->
            moduleList.forEach { module -> Log.i("CVB", module.toString()) }
        }

        viewModel.departmentList.observe(this) { departmentList ->
            departmentList.forEach { department -> Log.i("CVB", department.toString()) }
        }

        viewModel.userList.observe(this) { userList ->
            userList.forEach { user -> Log.i("CVB", user.toString()) }
        }

        viewModel.userModuleList.observe(this) { userModuleList ->
            userModuleList.forEach { userModule -> Log.i("CVB", userModule.toString()) }
        }

        viewModel.userDepartmentJoinList.observe(this) { userDepartmentJoinList ->
            userDepartmentJoinList.forEach { userDepartmentJoin -> Log.i("CVB", userDepartmentJoin.toString()) }
        }

        viewModel.userModuleJoinList.observe(this) { userModuleJoinList ->
            userModuleJoinList.forEach { userModuleJoin -> Log.i("CVB", userModuleJoin.toString()) }
        }

        viewModel.loadData()
    }
}