package com.bharath.expensetracker.di

import android.app.Application
import androidx.room.Room
import com.bharath.expensetracker.common.Cons
import com.bharath.expensetracker.data.database.DataBase
import com.bharath.expensetracker.data.deleted.RDDataBase
import com.bharath.expensetracker.data.deleted.Rd_REpo
import com.bharath.expensetracker.data.deleted.Rd_RepoImpl
import com.bharath.expensetracker.data.repository.Repository
import com.bharath.expensetracker.data.repository.RepositoryInterface
import com.bharath.expensetracker.screens.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule{

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

    @Provides
    @Singleton
    fun provideRdDataBase(app: Application):RDDataBase{
        return Room.databaseBuilder(
            app,
            RDDataBase::class.java,
            Cons.Rd_data_baseName
        ).build()
    }

    @Singleton
    @Provides
    fun provideRdRepository(R_db:RDDataBase):Rd_REpo =Rd_RepoImpl(R_db.daoRd)

    @Singleton
    @Provides
    fun provideHomeViewModel(repositoryInterface: RepositoryInterface):HomeViewModel = HomeViewModel(repositoryInterface)
}