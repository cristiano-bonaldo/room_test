package cvb.com.br.room_test.data.datasource

import cvb.com.br.room_test.db.entity.User

interface UserDataSource {

    suspend fun insert(user: User)

    suspend fun getList(): List<User>

}