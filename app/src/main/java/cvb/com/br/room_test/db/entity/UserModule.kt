package cvb.com.br.room_test.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_module", primaryKeys = ["user_id", "module_id"])
data class UserModule(
    @ColumnInfo(name = "user_id")
    val userId: Long,
    @ColumnInfo(name = "module_id")
    val moduleId: Long
)
