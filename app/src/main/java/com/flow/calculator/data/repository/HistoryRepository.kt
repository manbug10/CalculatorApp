package com.flow.calculator.data.repository

import com.flow.calculator.data.model.CalculationHistory
import kotlinx.coroutines.flow.Flow

/**
 * Repository for calculation history operations
 */
interface HistoryRepository {
    fun getHistory(): Flow<List<CalculationHistory>>
    suspend fun addHistoryEntry(expression: String, result: String)
    suspend fun clearHistory()
    suspend fun deleteEntry(id: Long)
}
