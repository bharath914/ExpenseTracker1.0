package com.bharath.expensetracker.di

import android.app.Application
import androidx.room.Room
import com.bharath.expensetracker.common.Cons
import com.bharath.expensetracker.database.repository.DaoName
import com.bharath.expensetracker.database.repository.NameDB
import com.bharath.expensetracker.database.repository.RepositroyImpl
import com.bharath.expensetracker.database.repository.repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NameModule {
    @Provides
    @Singleton
    fun provideNameDataBase(app: Application): NameDB {
        return Room.databaseBuilder(
            app,
            NameDB::class.java,
            Cons.name_db,
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(daoName: DaoName): repository {
        return RepositroyImpl(daoName)
    }

}