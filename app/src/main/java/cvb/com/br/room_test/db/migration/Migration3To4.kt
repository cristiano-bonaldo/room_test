package cvb.com.br.room_test.db.migration

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlin.random.Random

class Migration3To4 : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE module (id INTEGER NOT NULL PRIMARY KEY, name TEXT NOT NULL) ")
        database.execSQL("CREATE TABLE department (id INTEGER NOT NULL PRIMARY KEY, name TEXT NOT NULL) ")
        database.execSQL("CREATE TABLE user_module (user_id INTEGER NOT NULL, module_id INTEGER NOT NULL, PRIMARY KEY(user_id, module_id))")

        database.execSQL("ALTER TABLE user ADD COLUMN department_id INTEGER DEFAULT 1 NOT NULL")

        runScript(database)
    }

    private fun runScript(database: SupportSQLiteDatabase) {
        (1..3).forEach { idx ->
            val cv = ContentValues()
            cv.put("id", "$idx")
            cv.put("name", "module[$idx]")

            database.insert("module", SQLiteDatabase.CONFLICT_REPLACE, cv)
        }

        (1..2).forEach { idx ->
            val cv = ContentValues()
            cv.put("id", "$idx")
            cv.put("name", "dpt..[$idx]")

            database.insert("department", SQLiteDatabase.CONFLICT_REPLACE, cv)
        }

        database.execSQL("UPDATE user SET department_id = 2")

        for(user in 1..10 step 2) {
            val modules = Random.nextInt(1, 3)

            for (module in 1..modules) {
                val cv = ContentValues()
                cv.put("user_id", user)
                cv.put("module_id", module)

                database.insert("user_module", SQLiteDatabase.CONFLICT_REPLACE, cv)
            }
        }
    }

}