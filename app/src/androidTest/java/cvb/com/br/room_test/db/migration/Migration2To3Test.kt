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
class Migration2To3Test {

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDataBase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    private val TEST_DB = "migration_test.db"

    @Test
    fun migration_from_2_to_3_columns_validation() {
        helper.createDatabase(TEST_DB, 2).apply {
            close()
        }

        // Re-open the database with next version
        val db = helper.runMigrationsAndValidate(TEST_DB, 3, true, Migration2To3())

        val cursor = db.query("SELECT * FROM user")
        val columns = arrayOf("id", "name", "e_mail", "created_at", "is_admin", "login")

        Assert.assertArrayEquals(cursor.columnNames, columns)

        db.close()
    }

    @Test
    fun migration_from_2_to_3_data_validation() {
        val id = 1L
        val name = "cristiano"
        val email = "cris@teste.com.br"
        val createdAt = System.currentTimeMillis()
        val isAdmin = 0

        helper.createDatabase(TEST_DB, 2).apply {
            val cv = ContentValues()
            cv.put("id", id)
            cv.put("name", name)
            cv.put("email", email)
            cv.put("created_at", createdAt)
            cv.put("is_admin", isAdmin)

            insert("user", SQLiteDatabase.CONFLICT_REPLACE, cv)
            close()
        }

        // Re-open the database with next version.
        val db = helper.runMigrationsAndValidate(TEST_DB, 3, true, Migration2To3())

        val cursor = db.query("SELECT * FROM user")

        cursor.moveToFirst()

        val colId = cursor.getLong(cursor.getColumnIndex("id"))
        val colName = cursor.getString(cursor.getColumnIndex("name"))
        val colEmail = cursor.getString(cursor.getColumnIndex("e_mail"))
        val colCreatedAt = cursor.getLong(cursor.getColumnIndex("created_at"))
        val colIsAdmin = cursor.getInt(cursor.getColumnIndex("is_admin"))
        val colLogin = cursor.getString(cursor.getColumnIndex("login"))

        Assert.assertEquals(id, colId)
        Assert.assertEquals(name, colName)
        Assert.assertEquals(email, colEmail)
        Assert.assertEquals(createdAt, colCreatedAt)
        Assert.assertEquals(colIsAdmin, 0)
        Assert.assertTrue(colLogin.contains("#"))

        db.close()
    }

}