package cvb.com.br.room_test.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import cvb.com.br.room_test.db.data.UserDepartmentJoin
import cvb.com.br.room_test.db.data.UserModuleJoin

@Dao
interface UserModuleJoinDao {
    @Transaction
    @Query("SELECT * FROM User")
    suspend fun getList(): List<UserModuleJoin>
}