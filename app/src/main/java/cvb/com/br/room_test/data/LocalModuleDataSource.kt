package cvb.com.br.room_test.data

import cvb.com.br.room_test.data.datasource.ModuleDataSource
import cvb.com.br.room_test.db.dao.ModuleDao
import cvb.com.br.room_test.db.entity.Module
import javax.inject.Inject

class LocalModuleDataSource @Inject constructor(private val moduleDao: ModuleDao) : ModuleDataSource {

    override suspend fun getList(): List<Module> {
        return moduleDao.getList()
    }
}