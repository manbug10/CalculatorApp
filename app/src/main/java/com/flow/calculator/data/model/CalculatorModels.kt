package com.flow.calculator.data.model

/**
 * Represents a calculation history entry
 */
data class CalculationHistory(
    val id: Long = 0,
    val expression: String,
    val result: String,
    val timestamp: Long = System.currentTimeMillis()
)

/**
 * Represents calculator button types
 */
enum class CalculatorButtonType {
    DIGIT,
    OPERATOR,
    FUNCTION,
    ACTION,
    MEMORY
}

/**
 * Represents a calculator button
 */
data class CalculatorButton(
    val label: String,
    val value: String,
    val type: CalculatorButtonType,
    val span: Int = 1
)
