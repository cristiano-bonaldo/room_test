package cvb.com.br.room_test.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cvb.com.br.room_test.data.datasource.UserDataSource
import cvb.com.br.room_test.db.entity.User

class UserDataSourceFake : UserDataSource {
    private val userList = mutableListOf<User>()

    private val userListLiveData = MutableLiveData<List<User>>(userList)

    override suspend fun insert(user: User) {
        userList.add(user)
        refreshData()
    }

    override suspend fun delete(user: User) {
        userList.remove(user)
        refreshData()
    }

    override suspend fun update(user: User) {
        val idx = userList.indexOf(user)
        userList.add(idx, user)
        refreshData()
    }

    override suspend fun getUser(userId: Long): User {
        return userList.first { user -> user.id == userId }
    }

    override suspend fun getList(): List<User> {
        return userList
    }

    override fun getObservableList(): LiveData<List<User>> {
        return userListLiveData
    }

    private fun refreshData() {
        userListLiveData.value = userList
    }
}