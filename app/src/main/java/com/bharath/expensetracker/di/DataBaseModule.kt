package com.bharath.expensetracker.di
//
//import android.app.Application
//import androidx.room.Room
//import com.bharath.expensetracker.common.Cons
//import com.bharath.expensetracker.data.dao.DaoDb
//import com.bharath.expensetracker.data.database.DataBase
//import com.bharath.expensetracker.data.repository.Repository
//import com.bharath.expensetracker.data.repository.RepositoryInterface
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object DataBaseModule {
//
////    @Provides
////    @Singleton
////    fun provideRepository(dao: DaoDb): RepositoryInterface = Repository(dao)
////
////
////    @Provides
////    @Singleton
////    fun provideDataBase(app: Application): DataBase {
////        return Room.databaseBuilder(
////            app,
////            DataBase::class.java,
////            Cons.database_Name
////        ).build()
////    }
//
//
//}