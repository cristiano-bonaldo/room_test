package cvb.com.br.room_test.db.migration

import android.database.Cursor
import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration2To3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE user RENAME COLUMN email TO e_mail")
        database.execSQL("ALTER TABLE user ADD COLUMN login TEXT DEFAULT \"123\" NOT NULL")

        runScript(database)
    }

    private fun runScript(database: SupportSQLiteDatabase) {
        var c: Cursor? = null
        try {
            c = database.query("SELECT * FROM user WHERE login = \"123\"")
            Log.i("CVB", "Update V3-> ${c.count}")

            while (c.moveToNext()) {
                val idx = c.getColumnIndex("id")
                val id = c.getInt(idx)
                database.execSQL("UPDATE user SET login = \"login#${id}\" WHERE id = $id")
            }
        } finally {
            c?.close()
        }
    }

}