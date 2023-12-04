package com.bharath.expensetracker.domain.usecases


import com.bharath.expensetracker.data.model.KhataEntity
import com.bharath.expensetracker.data.repository.RepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class insertIntoKhataUseCase @Inject constructor(
    private val repository: RepositoryInterface,
) {

    suspend operator fun invoke(khataEntity: KhataEntity) {
        repository.insertKhata(khataEntity)
    }
}

class updateIntoKhataUseCase @Inject constructor(
    private val repository: RepositoryInterface,
) {

    suspend operator fun invoke(khataEntity: KhataEntity) {
        repository.updateKhata(khataEntity)
    }
}

class deleteIntoKhataUseCase @Inject constructor(
    private val repository: RepositoryInterface,
) {

    suspend operator fun invoke(khataEntity: KhataEntity) {
        repository.updateKhata(khataEntity)
    }
}

class getaAllKhataUseCase @Inject constructor(
    private val repository: RepositoryInterface,
) {

    suspend operator fun invoke(): Flow<List<KhataEntity>> = repository.getAllKhatas()
}
