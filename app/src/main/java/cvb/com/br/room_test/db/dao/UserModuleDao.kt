package cvb.com.br.room_test.db.dao

import androidx.room.Dao
import androidx.room.Query
import cvb.com.br.room_test.db.entity.UserModule

@Dao
interface UserModuleDao {
    @Query("SELECT * FROM user_module ORDER BY user_id ASC, module_id ASC")
    suspend fun getList(): List<UserModule>
}