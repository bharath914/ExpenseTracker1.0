package com.bharath.expensetracker.ui.theme.onboarding.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bharath.expensetracker.common.Cons
import com.bharath.expensetracker.data.dao.DaoDb
import com.bharath.expensetracker.data.database.DataBase
import com.bharath.expensetracker.data.repository.Repository
import com.bharath.expensetracker.data.repository.RepositoryInterface
import com.bharath.expensetracker.ui.theme.onboarding.data.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object OnBoardModule{
    @Provides
    @Singleton
    fun provideDataStoreRepo(@ApplicationContext context:Context)= DataStoreRepository(context)

    @Provides
    @Singleton
    fun provideRepository(db: DataBase): RepositoryInterface = Repository(db.dao)


    @Provides
    @Singleton
    fun provideDataBase(app: Application): DataBase {
        return Room.databaseBuilder(
            app,
            DataBase::class.java,
            Cons.database_Name
        ).build()
    }


}