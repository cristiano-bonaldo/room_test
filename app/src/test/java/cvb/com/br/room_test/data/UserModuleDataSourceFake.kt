package cvb.com.br.room_test.data

import cvb.com.br.room_test.data.datasource.UserModuleDataSource
import cvb.com.br.room_test.db.entity.UserModule

class UserModuleDataSourceFake : UserModuleDataSource {
    override suspend fun getList(): List<UserModule> {
        TODO("Not yet implemented")
    }
}