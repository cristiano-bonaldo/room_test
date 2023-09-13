package cvb.com.br.room_test.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val name: String = "",

    @ColumnInfo(name="e_mail")
    val email: String = "",

    @ColumnInfo(name="created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name="is_admin")
    val isAdmin: Int = 0,

    val login: String = "123"
)
