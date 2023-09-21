package cvb.com.br.room_test.di

import android.content.Context
import androidx.room.Room
import cvb.com.br.room_test.data.LocalDepartmentDataSource
import cvb.com.br.room_test.data.LocalModuleDataSource
import cvb.com.br.room_test.data.LocalUserDataSource
import cvb.com.br.room_test.data.LocalUserDepartmentJoinDataSource
import cvb.com.br.room_test.data.LocalUserModuleDataSource
import cvb.com.br.room_test.data.LocalUserModuleJoinDataSource
import cvb.com.br.room_test.data.datasource.DepartmentDataSource
import cvb.com.br.room_test.data.datasource.ModuleDataSource
import cvb.com.br.room_test.data.datasource.UserDataSource
import cvb.com.br.room_test.data.datasource.UserDepartmentJoinDataSource
import cvb.com.br.room_test.data.datasource.UserModuleDataSource
import cvb.com.br.room_test.data.datasource.UserModuleJoinDataSource
import cvb.com.br.room_test.db.AppDataBase
import cvb.com.br.room_test.db.dao.DepartmentDao
import cvb.com.br.room_test.db.dao.ModuleDao
import cvb.com.br.room_test.db.dao.UserDao
import cvb.com.br.room_test.db.dao.UserDepartmentJoinDao
import cvb.com.br.room_test.db.dao.UserModuleDao
import cvb.com.br.room_test.db.dao.UserModuleJoinDao
import cvb.com.br.room_test.db.migration.Migration1To2
import cvb.com.br.room_test.db.migration.Migration2To3
import cvb.com.br.room_test.db.migration.Migration3To4
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "RssReader"
        ).addCallback(AppDataBase.databaseCallback)
            .addMigrations(
                Migration1To2(),
                Migration2To3(),
                Migration3To4()
            )
            .build()
    }

    //==============

    @Provides
    fun providesUserDao(appDatabase: AppDataBase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun providesModuleDao(appDatabase: AppDataBase): ModuleDao {
        return appDatabase.moduleDao()
    }

    @Provides
    fun providesDepartmentDao(appDatabase: AppDataBase): DepartmentDao {
        return appDatabase.departmentDao()
    }

    @Provides
    fun providesUserModuleDao(appDatabase: AppDataBase): UserModuleDao {
        return appDatabase.userModuleDao()
    }

    @Provides
    fun providesUserModuleJoinDao(appDatabase: AppDataBase): UserModuleJoinDao {
        return appDatabase.userModuleJoinDao()
    }

    @Provides
    fun providesUserDepartmentJoinDao(appDatabase: AppDataBase): UserDepartmentJoinDao {
        return appDatabase.userDepartmentJoinDao()
    }

    //==============

    @UserDataSourceLocal
    @Singleton
    @Provides
    fun providesLocalUserDataSource(userDao: UserDao): UserDataSource = LocalUserDataSource(userDao)

    @ModuleDataSourceLocal
    @Singleton
    @Provides
    fun providesLocalModuleDataSource(moduleDao: ModuleDao): ModuleDataSource = LocalModuleDataSource(moduleDao)

    @DepartmentDataSourceLocal
    @Singleton
    @Provides
    fun providesLocalDepartmentDataSource(departmentDao: DepartmentDao): DepartmentDataSource = LocalDepartmentDataSource(departmentDao)

    @UserModuleDataSourceLocal
    @Singleton
    @Provides
    fun providesLocalUserModuleDataSource(userModuleDao: UserModuleDao): UserModuleDataSource = LocalUserModuleDataSource(userModuleDao)

    @UserModuleJoinDataSourceLocal
    @Singleton
    @Provides
    fun providesLocalUserModuleJoinDataSource(userModuleJoinDao: UserModuleJoinDao): UserModuleJoinDataSource = LocalUserModuleJoinDataSource(userModuleJoinDao)

    @UserDepartmentJoinDataSourceLocal
    @Singleton
    @Provides
    fun providesLocalUserDepartmentJoinDataSource(userDepartmentJoinDao: UserDepartmentJoinDao): UserDepartmentJoinDataSource = LocalUserDepartmentJoinDataSource(userDepartmentJoinDao)
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserDataSourceLocal
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ModuleDataSourceLocal

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DepartmentDataSourceLocal

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserModuleDataSourceLocal

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserModuleJoinDataSourceLocal

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserDepartmentJoinDataSourceLocal




