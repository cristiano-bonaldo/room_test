package cvb.com.br.room_test.db.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import cvb.com.br.room_test.db.entity.Department
import cvb.com.br.room_test.db.entity.Module
import cvb.com.br.room_test.db.entity.User
import cvb.com.br.room_test.db.entity.UserModule

data class UserModuleJoin(
    @Embedded val user: User,

    @Relation(
        parentColumn = "id",

        entity = Module::class,
        entityColumn = "id",
        associateBy = Junction(UserModule::class,
            parentColumn = "user_id",
            entityColumn = "module_id"
        )
    )
    val listModule: List<Module>
)
