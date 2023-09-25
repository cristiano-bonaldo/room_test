package cvb.com.br.room_test.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import cvb.com.br.room_test.data.PixabayDataSourceFake
import cvb.com.br.room_test.repository.PixabayRepository
import cvb.com.br.room_test.repository.UserRepository
import cvb.com.br.room_test.util.MainCoroutineRule
import cvb.com.br.room_test.util.getOrAwaitValue
import cvb.com.br.room_test.viewmodel.sealed.LoadPixabayStatus
import cvb.com.br.room_test.viewmodel.sealed.LoadUserStatus
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PixabayViewModelTest {

    // Run on the Main Thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Unit Test does not hava a Main Thread -> This rule allows to run the test as if it were in the Main Thread
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel: PixabayViewModel

    private lateinit var pixabayDataSource: PixabayDataSourceFake

    @Before
    fun setup() {
        // Fake - Pixabay Repository
        pixabayDataSource = PixabayDataSourceFake()
        val pixabayRepository = PixabayRepository(pixabayDataSource)

        viewModel = PixabayViewModel(
            pixabayRepository,
        )
    }

    @Test
    fun loadPixabayState_Loading_Success() {
        val listPixabayStatus = mutableListOf<LoadPixabayStatus>()
        viewModel.loadPixabayStatus.observeForever { status ->
            listPixabayStatus.add(status)
        }

        viewModel.loadPixabay()

        assertThat(listPixabayStatus[0]).isInstanceOf(LoadPixabayStatus.Loading::class.java)
        assertThat(listPixabayStatus[1]).isInstanceOf(LoadPixabayStatus.Success::class.java)
    }

    @Test
    fun loadPixabayState_Loading_Error() {
        pixabayDataSource.isError = true

        val listPixabayStatus = mutableListOf<LoadPixabayStatus>()
        viewModel.loadPixabayStatus.observeForever { status ->
            listPixabayStatus.add(status)
        }

        viewModel.loadPixabay()

        assertThat(listPixabayStatus[0]).isInstanceOf(LoadPixabayStatus.Loading::class.java)
        assertThat(listPixabayStatus[1]).isInstanceOf(LoadPixabayStatus.Error::class.java)
    }
}