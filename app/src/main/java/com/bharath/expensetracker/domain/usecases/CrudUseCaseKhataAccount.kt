package com.bharath.expensetracker.domain.usecases

import com.bharath.expensetracker.data.model.KhataAccountEntity
import com.bharath.expensetracker.data.repository.RepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class insertIntoKhataAccountUseCase @Inject constructor(
    private val repository: RepositoryInterface,
) {

    suspend operator fun invoke(khataAccountEntity: KhataAccountEntity) {
        repository.insertAccountForKhata(khataAccountEntity)
    }
}

class updateIntoKhataAccountUseCase @Inject constructor(
    private val repository: RepositoryInterface,
) {

    suspend operator fun invoke(khataAccountEntity: KhataAccountEntity) {
        repository.updateAccountForKhata(khataAccountEntity)
    }
}

class deleteIntoKhataAccountUseCase @Inject constructor(
    private val repository: RepositoryInterface,
) {

    suspend operator fun invoke(khataAccountEntity: KhataAccountEntity) {
        repository.deleteAccountForKhata(khataAccountEntity)
    }
}

class getaAllKhataAccountUseCase @Inject constructor(
    private val repository: RepositoryInterface,
) {

    suspend operator fun invoke(): Flow<List<KhataAccountEntity>> = repository.getAllAccounts()
}
