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
class Migration3To4Test {

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDataBase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    private val TEST_DB = "migration_test.db"

    @Test
    fun migration_from_3_to_4_columns_validation() {
        helper.createDatabase(TEST_DB, 3).apply {
            close()
        }

        // Re-open the database with next version
        val db = helper.runMigrationsAndValidate(TEST_DB, 4, true, Migration3To4())

        var cursor = db.query("SELECT * FROM user")
        var columns = arrayOf("id", "name", "e_mail", "created_at", "is_admin", "login", "department_id")
        Assert.assertArrayEquals(cursor.columnNames, columns)

        cursor = db.query("SELECT * FROM module")
        columns = arrayOf("id", "name")
        Assert.assertArrayEquals(cursor.columnNames, columns)

        cursor = db.query("SELECT * FROM department")
        columns = arrayOf("id", "name")
        Assert.assertArrayEquals(cursor.columnNames, columns)

        cursor = db.query("SELECT * FROM user_module")
        columns = arrayOf("user_id", "module_id")
        Assert.assertArrayEquals(cursor.columnNames, columns)

        db.close()
    }

    @Test
    fun migration_from_3_to_4_data_user_validation() {
        val id = 1L
        val name = "cristiano"
        val email = "cris@teste.com.br"
        val createdAt = System.currentTimeMillis()
        val isAdmin = 0
        val login = "cris#123"

        helper.createDatabase(TEST_DB, 3).apply {
            val cv = ContentValues()
            cv.put("id", id)
            cv.put("name", name)
            cv.put("e_mail", email)
            cv.put("created_at", createdAt)
            cv.put("is_admin", isAdmin)
            cv.put("login", login)

            insert("user", SQLiteDatabase.CONFLICT_REPLACE, cv)
            close()
        }

        // Re-open the database with next version.
        val db = helper.runMigrationsAndValidate(TEST_DB, 4, true, Migration3To4())

        val cursor = db.query("SELECT * FROM user")

        cursor.moveToFirst()

        val colId = cursor.getLong(cursor.getColumnIndex("id"))
        val colName = cursor.getString(cursor.getColumnIndex("name"))
        val colEmail = cursor.getString(cursor.getColumnIndex("e_mail"))
        val colCreatedAt = cursor.getLong(cursor.getColumnIndex("created_at"))
        val colIsAdmin = cursor.getInt(cursor.getColumnIndex("is_admin"))
        val colLogin = cursor.getString(cursor.getColumnIndex("login"))
        val colDepartmentId = cursor.getInt(cursor.getColumnIndex("department_id"))

        Assert.assertEquals(id, colId)
        Assert.assertEquals(name, colName)
        Assert.assertEquals(email, colEmail)
        Assert.assertEquals(createdAt, colCreatedAt)
        Assert.assertEquals(colIsAdmin, 0)
        Assert.assertTrue(colLogin.contains("#"))
        Assert.assertEquals(colDepartmentId, 2)

        db.close()
    }

    @Test
    fun migration_from_3_to_4_data_module_validation() {
        helper.createDatabase(TEST_DB, 3).apply {
            close()
        }

        // Re-open the database with next version.
        val db = helper.runMigrationsAndValidate(TEST_DB, 4, true, Migration3To4())

        db.delete("module", null, null)

        val cv = ContentValues()
        cv.put("id", 1)
        cv.put("name", "module-1")
        db.insert("module", SQLiteDatabase.CONFLICT_REPLACE, cv)

        val cursor = db.query("SELECT * FROM module")

        Assert.assertTrue(cursor.count == 1)

        cursor.moveToFirst()
        val colId = cursor.getLong(cursor.getColumnIndex("id"))
        val colName = cursor.getString(cursor.getColumnIndex("name"))

        Assert.assertEquals(colId, 1)
        Assert.assertEquals(colName, "module-1")

        db.close()
    }

    @Test
    fun migration_from_3_to_4_data_department_validation() {
        helper.createDatabase(TEST_DB, 3).apply {
            close()
        }

        // Re-open the database with next version.
        val db = helper.runMigrationsAndValidate(TEST_DB, 4, true, Migration3To4())

        db.delete("department", null, null)

        val cv = ContentValues()
        cv.put("id", 1)
        cv.put("name", "dep-1")
        db.insert("department", SQLiteDatabase.CONFLICT_REPLACE, cv)

        val cursor = db.query("SELECT * FROM department")

        cursor.moveToFirst()

        val colId = cursor.getLong(cursor.getColumnIndex("id"))
        val colName = cursor.getString(cursor.getColumnIndex("name"))

        Assert.assertTrue(cursor.count == 1)

        Assert.assertEquals(colId, 1)
        Assert.assertEquals(colName, "dep-1")

        db.close()
    }

    @Test
    fun migration_from_3_to_4_data_user_module_validation() {
        helper.createDatabase(TEST_DB, 3).apply {
            close()
        }

        // Re-open the database with next version.
        val db = helper.runMigrationsAndValidate(TEST_DB, 4, true, Migration3To4())

        db.delete("user_module", null, null)

        val cv = ContentValues()
        cv.put("user_id", 3)
        cv.put("module_id", 5)
        db.insert("user_module", SQLiteDatabase.CONFLICT_REPLACE, cv)

        val cursor = db.query("SELECT * FROM user_module")

        cursor.moveToFirst()

        Assert.assertTrue(cursor.count == 1)

        val colUserId   = cursor.getLong(cursor.getColumnIndex("user_id"))
        val colModuleId = cursor.getLong(cursor.getColumnIndex("module_id"))

        Assert.assertEquals(colUserId, 3)
        Assert.assertEquals(colModuleId, 5)

        db.close()
    }
}
