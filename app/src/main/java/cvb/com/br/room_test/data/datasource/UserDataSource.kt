package cvb.com.br.room_test.data.datasource

import androidx.lifecycle.LiveData
import cvb.com.br.room_test.db.entity.User

interface UserDataSource {

    suspend fun insert(user: User)

    suspend fun delete(user: User)

    suspend fun update(user: User)

    suspend fun getUser(userId: Long): User

    suspend fun getList(): List<User>

    fun getObservableList(): LiveData<List<User>>
}