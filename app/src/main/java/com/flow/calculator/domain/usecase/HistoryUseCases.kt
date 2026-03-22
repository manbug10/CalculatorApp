package com.flow.calculator.domain.usecase

import com.flow.calculator.data.repository.HistoryRepository
import com.flow.calculator.data.model.CalculationHistory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Use case for getting calculation history
 */
@Singleton
class GetHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    operator fun invoke(): Flow<List<CalculationHistory>> = historyRepository.getHistory()
}

/**
 * Use case for adding a history entry
 */
@Singleton
class AddHistoryEntryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke(expression: String, result: String) = 
        historyRepository.addHistoryEntry(expression, result)
}

/**
 * Use case for clearing history
 */
@Singleton
class ClearHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke() = historyRepository.clearHistory()
}
