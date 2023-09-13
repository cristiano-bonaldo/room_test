package cvb.com.br.room_test.db.migration

import android.database.Cursor
import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE user ADD COLUMN is_admin INTEGER NOT NULL")

        runScript(database)
    }

    private fun runScript(database: SupportSQLiteDatabase) {
        var c: Cursor? = null
        try {
            c = database.query("SELECT * FROM user WHERE is_admin = 0 AND id IN (1,2)")
            Log.i("CVB", "Update V2-> ${c.count}")

            while (c.moveToNext()) {
                val idx = c.getColumnIndex("id")
                val id = c.getInt(idx)
                database.execSQL("UPDATE user SET is_admin = 1 WHERE id = $id")
            }
        } finally {
            c?.close()
        }
    }

}