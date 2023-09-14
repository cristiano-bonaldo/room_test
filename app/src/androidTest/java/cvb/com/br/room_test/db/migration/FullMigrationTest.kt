package cvb.com.br.room_test.db.migration

import androidx.room.Room
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
class FullMigrationTest {

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDataBase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    private val TEST_DB = "migration_test.db"

    // Array of all migrations.
    private val ALL_MIGRATIONS = arrayOf(
        Migration1To2(),
        Migration2To3(),
        Migration3To4(),
    )

    @Test
    fun migrateAll() {
        // Create earliest version of the database.
        helper.createDatabase(TEST_DB, 1).apply {
            close()
        }

        // Open latest version of the database.
        // Room validates the schema once all migrations execute.
        val db = Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDataBase::class.java,
            TEST_DB
        ).addMigrations(*ALL_MIGRATIONS).build()

        var cursor = db.query("SELECT * FROM user", null)
        // User Columns = "id", "name", "e_mail", "created_at", "is_admin", login, department_id
        Assert.assertTrue(cursor.columnNames.size == 7)

        cursor = db.query("SELECT * FROM module", null)
        // Module Columns = "id", "name"
        Assert.assertTrue(cursor.columnNames.size == 2)

        cursor = db.query("SELECT * FROM department", null)
        // Department Columns = "id", "name"
        Assert.assertTrue(cursor.columnNames.size == 2)

        cursor = db.query("SELECT * FROM user_module", null)
        // User_Module Columns = "user_id", "module_id"
        Assert.assertTrue(cursor.columnNames.size == 2)

        db.close()
    }
}