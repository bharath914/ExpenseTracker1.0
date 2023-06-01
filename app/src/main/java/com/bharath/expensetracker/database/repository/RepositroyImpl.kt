package com.bharath.expensetracker.database.repository

import com.bharath.expensetracker.database.data.User

class RepositroyImpl(
    private val dao: DaoName
) :repository{
   override suspend fun insertName(user: User){
        dao.insertName(user)
   }
    override suspend fun getName(): String {
     return dao.getName()
    }

}