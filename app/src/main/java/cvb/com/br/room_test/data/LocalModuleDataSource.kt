package cvb.com.br.room_test.data

import android.content.Context
import cvb.com.br.room_test.data.datasource.ModuleDataSource
import cvb.com.br.room_test.db.AppDataBase
import cvb.com.br.room_test.db.entity.Module

class LocalModuleDataSource(context: Context) : ModuleDataSource {

    private val moduleDao = AppDataBase.getDatabase(context).moduleDao()

    override suspend fun getList(): List<Module> {
        return moduleDao.getList()
    }
}