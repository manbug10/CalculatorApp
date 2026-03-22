package com.flow.calculator.data.repository.impl

import com.flow.calculator.data.model.CalculationHistory
import com.flow.calculator.data.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * In-memory implementation of HistoryRepository
 */
@Singleton
class HistoryRepositoryImpl @Inject constructor() : HistoryRepository {
    
    private val _history = MutableStateFlow<List<CalculationHistory>>(emptyList())
    override fun getHistory(): Flow<List<CalculationHistory>> = _history.asStateFlow()
    
    override suspend fun addHistoryEntry(expression: String, result: String) {
        val entry = CalculationHistory(
            expression = expression,
            result = result
        )
        _history.value = listOf(entry) + _history.value.take(49) // Keep last 50 entries
    }
    
    override suspend fun clearHistory() {
        _history.value = emptyList()
    }
    
    override suspend fun deleteEntry(id: Long) {
        _history.value = _history.value.filter { it.id != id }
    }
}
