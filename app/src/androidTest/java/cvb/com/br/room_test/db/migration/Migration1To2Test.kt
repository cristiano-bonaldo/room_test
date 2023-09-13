package cvb.com.br.room_test.db.migration

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import cvb.com.br.room_test.db.AppDataBase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Migration1To2Test {

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDataBase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    private val TEST_DB = "migration_test.db"

    @Test
    fun migration_from_1_to_2_columns_validation() {
        helper.createDatabase(TEST_DB, 1).apply {
            close()
        }

        // Re-open the database with next version
        val db = helper.runMigrationsAndValidate(TEST_DB, 2, true, Migration1To2())

        val cursor = db.query("SELECT * FROM user")
        val columns = arrayOf("id", "name", "email", "created_at", "is_admin")

        Assert.assertArrayEquals(columns, cursor.columnNames)

        db.close()
    }

    @Test
    fun migration_from_1_to_2_data_validation() {
        val id = 1L
        val name = "cristiano"
        val email = "cris@teste.com.br"
        val createdAt = System.currentTimeMillis()

        helper.createDatabase(TEST_DB, 1).apply {
            val cv = ContentValues()
            cv.put("id", id)
            cv.put("name", name)
            cv.put("email", email)
            cv.put("created_at", createdAt)
            insert("user", SQLiteDatabase.CONFLICT_REPLACE, cv)
            close()
        }

        // Re-open the database with next version.
        val db = helper.runMigrationsAndValidate(TEST_DB, 2, true, Migration1To2())

        val cursor = db.query("SELECT * FROM user")

        cursor.moveToFirst()

        val colId = cursor.getLong(cursor.getColumnIndex("id"))
        val colName = cursor.getString(cursor.getColumnIndex("name"))
        val colEmail = cursor.getString(cursor.getColumnIndex("email"))
        val colCreatedAt = cursor.getLong(cursor.getColumnIndex("created_at"))
        val colIsAdmin = cursor.getInt(cursor.getColumnIndex("is_admin"))

        Assert.assertEquals(id, colId)
        Assert.assertEquals(name, colName)
        Assert.assertEquals(email, colEmail)
        Assert.assertEquals(createdAt, colCreatedAt)
        Assert.assertEquals(colIsAdmin, 1) //-> Script ID 1 e 2 -> isAdmin = 1 | Outros ID´s = 0

        db.close()
    }

}