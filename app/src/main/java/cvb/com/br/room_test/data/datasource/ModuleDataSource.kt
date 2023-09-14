package cvb.com.br.room_test.data.datasource

import cvb.com.br.room_test.db.entity.Module
import cvb.com.br.room_test.db.entity.User

interface ModuleDataSource {
    suspend fun getList(): List<Module>
}