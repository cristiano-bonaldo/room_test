package cvb.com.br.room_test.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import cvb.com.br.room_test.db.dao.DepartmentDao
import cvb.com.br.room_test.db.dao.ModuleDao
import cvb.com.br.room_test.db.dao.UserDao
import cvb.com.br.room_test.db.dao.UserDepartmentJoinDao
import cvb.com.br.room_test.db.dao.UserModuleDao
import cvb.com.br.room_test.db.dao.UserModuleJoinDao
import cvb.com.br.room_test.db.entity.Department
import cvb.com.br.room_test.db.entity.User
import cvb.com.br.room_test.db.entity.UserModule
import cvb.com.br.room_test.db.entity.Module
import cvb.com.br.room_test.db.migration.Migration1To2
import cvb.com.br.room_test.db.migration.Migration2To3
import cvb.com.br.room_test.db.migration.Migration3To4
import kotlin.random.Random

@Database(
    entities = [
        User::class,
        Module::class,
        Department::class,
        UserModule::class
    ],
    version = 4,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun moduleDao(): ModuleDao
    abstract fun departmentDao(): DepartmentDao
    abstract fun userModuleDao(): UserModuleDao
    abstract fun userDepartmentJoinDao(): UserDepartmentJoinDao
    abstract fun userModuleJoinDao(): UserModuleJoinDao


    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java,"app_database")
                    .addCallback(databaseCallback)
                    .addMigrations(
                        Migration1To2(),
                        Migration2To3(),
                        Migration3To4()
                    )
                    .build()

                INSTANCE = instance

                return instance
            }
        }

        private val databaseCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                Log.i("CVB", "Database - onCreate")

                insertDataDB(db)
            }

            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                super.onDestructiveMigration(db)
                Log.i("CVB", "Database - onDestructiveMigration")
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                Log.i("CVB", "Database - onOpen")
            }
        }

        private fun insertDataDB(db: SupportSQLiteDatabase) {
            (1..3).forEach { idx ->
                val cv = ContentValues()
                cv.put("name", "module[$idx]")

                db.insert("module", SQLiteDatabase.CONFLICT_REPLACE, cv)
            }

            (1..2).forEach { idx ->
                val cv = ContentValues()
                cv.put("name", "dpt..[$idx]")

                db.insert("department", SQLiteDatabase.CONFLICT_REPLACE, cv)
            }

            (1..10).forEach { idx ->
                val name  = "user-$idx"
                val mail  = "user-$idx@test.com"
                val login = "login#$idx"
                val date  = System.currentTimeMillis()
                val isAdmin = 0
                val idDepartment = if (idx % 2 > 0) { 1 } else { 2 }

                val cv = ContentValues()
                cv.put("name", name)
                cv.put("e_mail", mail)
                cv.put("created_at", date)
                cv.put("login", login)
                cv.put("is_admin", isAdmin)
                cv.put("department_id", idDepartment)

                db.insert("user", SQLiteDatabase.CONFLICT_REPLACE, cv)
            }


            for(user in 1..10 step 2) {
                val modules = Random.nextInt(1, 3)

                for (module in 1..modules) {
                    val cv = ContentValues()
                    cv.put("user_id", user)
                    cv.put("module_id", module)

                    db.insert("user_module", SQLiteDatabase.CONFLICT_REPLACE, cv)
                }
            }
        }
    }
}