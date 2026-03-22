package com.flow.calculator.domain.calculator

/**
 * Result of a calculator operation
 */
sealed class CalculatorResult {
    data class Success(val value: String) : CalculatorResult()
    data class Error(val message: String) : CalculatorResult()
    object InvalidOperation : CalculatorResult()
}

/**
 * Calculator mode
 */
enum class CalculatorMode {
    BASIC,
    SCIENTIFIC
}

/**
 * Memory operations for calculator
 */
data class CalculatorMemory(
    val value: Double = 0.0,
    val isStored: Boolean = false
)
