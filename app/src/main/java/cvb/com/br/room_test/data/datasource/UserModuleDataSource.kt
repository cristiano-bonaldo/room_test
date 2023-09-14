package cvb.com.br.room_test.data.datasource

import cvb.com.br.room_test.db.entity.UserModule

interface UserModuleDataSource {
    suspend fun getList(): List<UserModule>
}