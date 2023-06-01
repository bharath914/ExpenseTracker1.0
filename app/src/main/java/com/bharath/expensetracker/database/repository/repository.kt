package com.bharath.expensetracker.database.repository

import com.bharath.expensetracker.database.data.User

interface repository {
    suspend fun insertName(user: User)
    suspend fun getName():String
}