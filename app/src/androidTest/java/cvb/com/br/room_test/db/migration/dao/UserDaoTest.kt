package cvb.com.br.room_test.db.migration.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import cvb.com.br.room_test.db.AppDataBase
import cvb.com.br.room_test.db.dao.UserDao
import cvb.com.br.room_test.db.entity.User
import cvb.com.br.room_test.di.TestDatabase
import cvb.com.br.room_test.util.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class) -> We are using the HiltTestRunner class, that was defined in build.gradle (testInstrumentationRunner = "cvb.com.br.room_test.HiltTestRunner")
@SmallTest
@HiltAndroidTest
class UserDaoTest {

    // Run on the Main Thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Dagger Hilt -> Needs this rule
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    //private lateinit var db: AppDataBase -> Without Dagger Hilt
    @Inject
    @TestDatabase
    lateinit var db: AppDataBase

    private lateinit var userDao: UserDao
    @Before
    fun setup() {
        /*
        Without Dagger Hilt

        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries().build()
        */
        hiltRule.inject()

        userDao = db.userDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertUser() = runTest {
        val user = User(1, "teste", "teste@teste.com", System.currentTimeMillis(), 1, "ABCDE", 2)
        userDao.insert(user)

        val list = userDao.getList()

        val userFromList = list[0]

        assertThat(userFromList).isEqualTo(user)
    }

    @Test
    fun updateUser() = runTest {
        var user = User(1, "teste", "teste@teste.com", System.currentTimeMillis(), 1, "ABCDE", 2)
        userDao.insert(user)

        user = User(1, "user", "user@teste.com", System.currentTimeMillis(), 2, "12345", 1)
        userDao.update(user)

        val list = userDao.getList()

        val userFromList = list[0]

        assertThat(userFromList).isEqualTo(user)
    }

    @Test
    fun deleteUser() = runTest {
        val user = User(1, "teste", "teste@teste.com")
        userDao.insert(user)

        var list = userDao.getList()

        assertThat(list.size).isEqualTo(1)

        userDao.delete(user)

        list = userDao.getList()

        assertThat(list.size).isEqualTo(0)
    }

    @Test
    fun getListUser() = runTest {
        val user1 = User(1, "teste1", "teste1@teste.com")
        userDao.insert(user1)

        val user2 = User(2, "teste2", "teste2@teste.com")
        userDao.insert(user2)

        val user3 = User(3, "teste3", "teste3@teste.com")
        userDao.insert(user3)

        val user4 = User(4, "teste4", "teste4@teste.com")
        userDao.insert(user4)

        val list = userDao.getList()

        assertThat(list.size).isEqualTo(4)
    }

    @Test
    fun getUser() = runTest {
        val user1 = User(1, "teste1", "teste1@teste.com")
        userDao.insert(user1)

        val user2 = User(2, "teste2", "teste2@teste.com")
        userDao.insert(user2)

        val user3 = User(3, "teste3", "teste3@teste.com")
        userDao.insert(user3)

        var userFromList = userDao.getUser(2)
        assertThat(userFromList).isEqualTo(user2)

        userFromList = userDao.getUser(3)
        assertThat(userFromList).isEqualTo(user3)

        userFromList = userDao.getUser(1)
        assertThat(userFromList).isEqualTo(user1)
    }
    @Test
    fun getObservableListUser() = runTest {
        val user1 = User(1, "teste1", "teste1@teste.com")
        userDao.insert(user1)

        val user2 = User(2, "teste2", "teste2@teste.com")
        userDao.insert(user2)

        val user3 = User(3, "teste3", "teste3@teste.com")
        userDao.insert(user3)

        val user4 = User(4, "teste4", "teste4@teste.com")
        userDao.insert(user4)

        val list = userDao.getObservableList().getOrAwaitValue()

        assertThat(list.size).isEqualTo(4)
    }

}
