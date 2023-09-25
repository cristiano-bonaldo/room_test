package cvb.com.br.room_test.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import cvb.com.br.room_test.data.DepartmentDataSourceFake
import cvb.com.br.room_test.data.ModuleDataSourceFake
import cvb.com.br.room_test.data.PixabayDataSourceFake
import cvb.com.br.room_test.data.UserDataSourceFake
import cvb.com.br.room_test.data.UserDepartmentJoinDataSourceFake
import cvb.com.br.room_test.data.UserModuleDataSourceFake
import cvb.com.br.room_test.data.UserModuleJoinDataSourceFake
import cvb.com.br.room_test.repository.DepartmentRepository
import cvb.com.br.room_test.repository.ModuleRepository
import cvb.com.br.room_test.repository.UserDepartmentJoinRepository
import cvb.com.br.room_test.repository.UserModuleJoinRepository
import cvb.com.br.room_test.repository.UserModuleRepository
import cvb.com.br.room_test.repository.UserRepository
import cvb.com.br.room_test.util.MainCoroutineRule
import cvb.com.br.room_test.util.getOrAwaitValue
import cvb.com.br.room_test.viewmodel.sealed.LoadUserStatus
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    // Run on the Main Thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Unit Test does not hava a Main Thread -> This rule allows to run the test as if it were in the Main Thread
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        // Fake - User Repository
        val userDataSource = UserDataSourceFake()
        val userRepository = UserRepository(userDataSource)

        viewModel = MainViewModel(
            userRepository,

            ModuleRepository(ModuleDataSourceFake()),
            DepartmentRepository(DepartmentDataSourceFake()),
            UserModuleRepository(UserModuleDataSourceFake()),
            UserDepartmentJoinRepository(UserDepartmentJoinDataSourceFake()),
            UserModuleJoinRepository(UserModuleJoinDataSourceFake())
        )
    }

    @Test
    fun loadUserState_Success() {
        viewModel.loadUser()

        val userStatus = viewModel.loadUserStatus.getOrAwaitValue()

        assertThat(userStatus).isInstanceOf(LoadUserStatus.Success::class.java)
    }

    @Test
    fun loadUserState_Loading_Success() {
        val listUserStatus = mutableListOf<LoadUserStatus>()
        viewModel.loadUserStatus.observeForever { status ->
            listUserStatus.add(status)
        }

        viewModel.loadUser()

        assertThat(listUserStatus[0]).isInstanceOf(LoadUserStatus.Loading::class.java)
        assertThat(listUserStatus[1]).isInstanceOf(LoadUserStatus.Success::class.java)
    }
}