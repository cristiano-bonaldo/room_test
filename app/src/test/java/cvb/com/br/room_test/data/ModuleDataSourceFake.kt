package cvb.com.br.room_test.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cvb.com.br.room_test.data.datasource.DepartmentDataSource
import cvb.com.br.room_test.data.datasource.ModuleDataSource
import cvb.com.br.room_test.data.datasource.UserDataSource
import cvb.com.br.room_test.db.entity.Department
import cvb.com.br.room_test.db.entity.Module
import cvb.com.br.room_test.db.entity.User

class ModuleDataSourceFake : ModuleDataSource {
    override suspend fun getList(): List<Module> {
        TODO("Not yet implemented")
    }
}