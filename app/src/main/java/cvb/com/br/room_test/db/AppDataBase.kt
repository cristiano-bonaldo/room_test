package cvb.com.br.room_test.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import cvb.com.br.room_test.db.dao.UserDao
import cvb.com.br.room_test.db.entity.User
import cvb.com.br.room_test.db.migration.Migration1To2
import cvb.com.br.room_test.db.migration.Migration2To3

@Database(
    entities = [User::class],
    version = 3,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java,"app_database")
                    .addCallback(databaseCallback)
                    .addMigrations(
                        Migration1To2(),
                        Migration2To3()
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

                (1..10).forEach { idx ->
                    val name  = "user-$idx"
                    val mail  = "user-$idx@test.com"
                    val login = "login#$idx"
                    val date  = System.currentTimeMillis()
                    val isAdmin = 0

                    val cv = ContentValues()
                    cv.put("name", name)
                    cv.put("e_mail", mail)
                    cv.put("created_at", date)
                    cv.put("login", login)
                    cv.put("is_admin", isAdmin)

                    db.insert("user", SQLiteDatabase.CONFLICT_REPLACE, cv)
                }
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
    }
}