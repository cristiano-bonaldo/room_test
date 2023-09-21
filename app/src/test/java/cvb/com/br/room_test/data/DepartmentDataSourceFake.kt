package cvb.com.br.room_test.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cvb.com.br.room_test.data.datasource.DepartmentDataSource
import cvb.com.br.room_test.data.datasource.UserDataSource
import cvb.com.br.room_test.db.entity.Department
import cvb.com.br.room_test.db.entity.User

class DepartmentDataSourceFake : DepartmentDataSource {
    override suspend fun getList(): List<Department> {
        TODO("Not yet implemented")
    }
}