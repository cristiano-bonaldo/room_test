package cvb.com.br.room_test.data.datasource

import cvb.com.br.room_test.db.data.UserDepartmentJoin

interface UserDepartmentJoinDataSource {

    suspend fun getList(): List<UserDepartmentJoin>

}