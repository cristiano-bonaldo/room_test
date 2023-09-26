package cvb.com.br.room_test.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import cvb.com.br.room_test.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModuleTest {

    @TestDatabase
    @Provides
    // @Singleton -> For TEST purpose, we do not use Singleton -> We want to create a new DB instance for each test
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries().build()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TestDatabase
