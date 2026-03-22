package com.flow.calculator.domain.calculator

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.*

/**
 * Core calculator engine that performs all calculations
 */
@Singleton
class CalculatorEngine @Inject constructor() {
    
    fun evaluate(expression: String): CalculatorResult {
        return try {
            val result = calculate(expression)
            if (result.isNaN() || result.isInfinite()) {
                CalculatorResult.Error("Invalid result")
            } else {
                CalculatorResult.Success(formatResult(result))
            }
        } catch (e: Exception) {
            CalculatorResult.Error("Error: ${e.message}")
        }
    }
    
    private fun calculate(expression: String): Double {
        val sanitized = expression
            .replace("×", "*")
            .replace("÷", "/")
            .replace("π", Math.PI.toString())
            .replace("e", Math.E.toString())
            .replace("√", "sqrt")
            .replace("^", "pow")
        
        return evaluateExpression(sanitized)
    }
    
    private fun evaluateExpression(expression: String): Double {
        return when {
            expression.contains("+") -> {
                val parts = expression.split("+", limit = 2)
                if (parts.size == 2) evaluateExpression(parts[0]) + evaluateExpression(parts[1])
                else expression.toDoubleOrNull() ?: 0.0
            }
            expression.contains("-") -> {
                val parts = expression.split("-", limit = 2)
                if (parts.size == 2) evaluateExpression(parts[0]) - evaluateExpression(parts[1])
                else expression.toDoubleOrNull() ?: 0.0
            }
            expression.contains("*") -> {
                val parts = expression.split("*", limit = 2)
                if (parts.size == 2) evaluateExpression(parts[0]) * evaluateExpression(parts[1])
                else expression.toDoubleOrNull() ?: 0.0
            }
            expression.contains("/") -> {
                val parts = expression.split("/", limit = 2)
                if (parts.size == 2) {
                    val divisor = evaluateExpression(parts[1])
                    if (divisor == 0.0) throw ArithmeticException("Division by zero")
                    evaluateExpression(parts[0]) / divisor
                } else expression.toDoubleOrNull() ?: 0.0
            }
            expression.contains("%") -> {
                val value = expression.replace("%", "").toDoubleOrNull() ?: 0.0
                value / 100.0
            }
            expression.startsWith("sqrt") -> {
                val value = expression.removePrefix("sqrt").removePrefix("(").removeSuffix(")").toDoubleOrNull() ?: 0.0
                sqrt(value)
            }
            expression.contains("pow") -> {
                val parts = expression.split("pow")
                if (parts.size == 2) {
                    pow(evaluateExpression(parts[0]), evaluateExpression(parts[1]))
                } else expression.toDoubleOrNull() ?: 0.0
            }
            expression.startsWith("sin") -> {
                val value = expression.removePrefix("sin").removePrefix("(").removeSuffix(")").toDoubleOrNull() ?: 0.0
                sin(Math.toRadians(value))
            }
            expression.startsWith("cos") -> {
                val value = expression.removePrefix("cos").removePrefix("(").removeSuffix(")").toDoubleOrNull() ?: 0.0
                cos(Math.toRadians(value))
            }
            expression.startsWith("tan") -> {
                val value = expression.removePrefix("tan").removePrefix("(").removeSuffix(")").toDoubleOrNull() ?: 0.0
                tan(Math.toRadians(value))
            }
            expression.startsWith("log") -> {
                val value = expression.removePrefix("log").removePrefix("(").removeSuffix(")").toDoubleOrNull() ?: 0.0
                log10(value)
            }
            expression.startsWith("ln") -> {
                val value = expression.removePrefix("ln").removePrefix("(").removeSuffix(")").toDoubleOrNull() ?: 0.0
                ln(value)
            }
            else -> expression.toDoubleOrNull() ?: 0.0
        }
    }
    
    private fun formatResult(value: Double): String {
        return when {
            value.isNaN() -> "Error"
            value.isInfinite() -> "Infinity"
            value == value.toLong().toDouble() -> value.toLong().toString()
            else -> {
                val formatted = String.format("%.10f", value).replace(",", ".")
                formatted.trimEnd('0').trimEnd('.')
            }
        }
    }
    
    // Basic operations
    fun add(a: Double, b: Double): Double = a + b
    fun subtract(a: Double, b: Double): Double = a - b
    fun multiply(a: Double, b: Double): Double = a * b
    fun divide(a: Double, b: Double): Result<Double> = 
        if (b == 0.0) Result.failure(ArithmeticException("Division by zero"))
        else Result.success(a / b)
    
    // Scientific operations
    fun square(value: Double): Double = value * value
    fun cube(value: Double): Double = value * value * value
    fun squareRoot(value: Double): Result<Double> = 
        if (value < 0) Result.failure(IllegalArgumentException("Cannot calculate square root of negative number"))
        else Result.success(sqrt(value))
    fun cubeRoot(value: Double): Double = cbrt(value)
    fun power(base: Double, exponent: Double): Double = base.pow(exponent)
    fun factorial(value: Int): Result<Long> {
        if (value < 0) return Result.failure(IllegalArgumentException("Factorial of negative number"))
        if (value > 20) return Result.failure(IllegalArgumentException("Number too large"))
        return Result.success((1..value).fold(1L) { acc, i -> acc * i })
    }
    
    // Trigonometric
    fun sin(value: Double, isDegrees: Boolean = true): Double = 
        kotlin.math.sin(if (isDegrees) Math.toRadians(value) else value)
    fun cos(value: Double, isDegrees: Boolean = true): Double = 
        kotlin.math.cos(if (isDegrees) Math.toRadians(value) else value)
    fun tan(value: Double, isDegrees: Boolean = true): Double = 
        kotlin.math.tan(if (isDegrees) Math.toRadians(value) else value)
    
    // Logarithmic
    fun log10(value: Double): Result<Double> = 
        if (value <= 0) Result.failure(IllegalArgumentException("Log of non-positive number"))
        else Result.success(log10(value))
    fun ln(value: Double): Result<Double> = 
        if (value <= 0) Result.failure(IllegalArgumentException("Log of non-positive number"))
        else Result.success(ln(value))
    
    // Constants
    fun getPi(): Double = Math.PI
    fun getE(): Double = Math.E
}
