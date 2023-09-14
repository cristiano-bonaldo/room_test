package cvb.com.br.room_test.data

import android.content.Context
import cvb.com.br.room_test.data.datasource.DepartmentDataSource
import cvb.com.br.room_test.db.AppDataBase
import cvb.com.br.room_test.db.entity.Department

class LocalDepartmentDataSource(context: Context) : DepartmentDataSource {

    private val departmentDao = AppDataBase.getDatabase(context).departmentDao()

    override suspend fun getList(): List<Department> {
        return departmentDao.getList()
    }
}