package cvb.com.br.room_test.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import cvb.com.br.room_test.db.dao.UserDao
import cvb.com.br.room_test.db.entity.User
import cvb.com.br.room_test.db.migration.Migration1To2

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java,"app_database")
                    .addCallback(databaseCallback)
                    //.addMigrations(Migration1To2())
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
                    val name = "user-$idx"
                    val mail = "user-$idx@test.com"
                    val date = System.currentTimeMillis()
                    db.execSQL("INSERT INTO user (name, email, created_at) values (\"$name\",\"$mail\",$date)")
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