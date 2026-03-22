package com.flow.calculator.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flow.calculator.domain.calculator.CalculatorEngine
import com.flow.calculator.domain.calculator.CalculatorMemory
import com.flow.calculator.domain.calculator.CalculatorMode
import com.flow.calculator.domain.calculator.CalculatorResult
import com.flow.calculator.domain.usecase.AddHistoryEntryUseCase
import com.flow.calculator.domain.usecase.ClearHistoryUseCase
import com.flow.calculator.domain.usecase.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI State for the calculator
 */
data class CalculatorUiState(
    val display: String = "0",
    val expression: String = "",
    val mode: CalculatorMode = CalculatorMode.BASIC,
    val memory: CalculatorMemory = CalculatorMemory(),
    val history: List<String> = emptyList(),
    val isHistoryVisible: Boolean = false,
    val isError: Boolean = false
)

/**
 * ViewModel for the calculator screen
 */
@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculatorEngine: CalculatorEngine,
    private val getHistoryUseCase: GetHistoryUseCase,
    private val addHistoryEntryUseCase: AddHistoryEntryUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()
    
    private var currentNumber = "0"
    private var currentExpression = ""
    private var lastOperation = ""
    private var isNewNumber = true
    private var isDegrees = true
    
    init {
        observeHistory()
    }
    
    private fun observeHistory() {
        viewModelScope.launch {
            getHistoryUseCase().collect { history ->
                _uiState.value = _uiState.value.copy(
                    history = history.map { "${it.expression} = ${it.result}" }
                )
            }
        }
    }
    
    fun onDigitClick(digit: String) {
        if (_uiState.value.isError) {
            resetCalculator()
        }
        
        currentNumber = if (currentNumber == "0" || isNewNumber) {
            digit
        } else {
            currentNumber + digit
        }
        isNewNumber = false
        updateDisplay()
    }
    
    fun onDecimalClick() {
        if (_uiState.value.isError) {
            resetCalculator()
            return
        }
        
        if (isNewNumber) {
            currentNumber = "0."
            isNewNumber = false
        } else if (!currentNumber.contains(".")) {
            currentNumber += "."
        }
        updateDisplay()
    }
    
    fun onOperatorClick(operator: String) {
        if (_uiState.value.isError) return
        
        lastOperation = operator
        currentExpression = if (currentExpression.isEmpty()) {
            "$currentNumber $operator"
        } else {
            "$currentExpression $currentNumber $operator"
        }
        isNewNumber = true
        updateDisplay()
    }
    
    fun onEqualsClick() {
        if (_uiState.value.isError) return
        
        val fullExpression = "$currentExpression$currentNumber"
        when (val result = calculatorEngine.evaluate(fullExpression)) {
            is CalculatorResult.Success -> {
                viewModelScope.launch {
                    addHistoryEntryUseCase(fullExpression, result.value)
                }
                currentNumber = result.value
                currentExpression = ""
                isNewNumber = true
                _uiState.value = _uiState.value.copy(
                    display = result.value,
                    expression = fullExpression,
                    isError = false
                )
            }
            is CalculatorResult.Error -> {
                _uiState.value = _uiState.value.copy(
                    display = result.message,
                    isError = true
                )
            }
            else -> {}
        }
    }
    
    fun onClearClick() {
        resetCalculator()
    }
    
    fun onClearEntryClick() {
        currentNumber = "0"
        updateDisplay()
    }
    
    fun onPercentClick() {
        if (_uiState.value.isError) return
        
        val value = currentNumber.toDoubleOrNull() ?: return
        currentNumber = (value / 100.0).toString()
        updateDisplay()
    }
    
    fun onNegateClick() {
        if (_uiState.value.isError) return
        
        currentNumber = when {
            currentNumber.startsWith("-") -> currentNumber.substring(1)
            currentNumber != "0" -> "-$currentNumber"
            else -> currentNumber
        }
        updateDisplay()
    }
    
    // Scientific operations
    fun onSquareClick() {
        applyUnaryOperation { calculatorEngine.square(it) }
    }
    
    fun onCubeClick() {
        applyUnaryOperation { calculatorEngine.cube(it) }
    }
    
    fun onSquareRootClick() {
        applyUnaryOperation { 
            calculatorEngine.squareRoot(it).getOrNull() ?: Double.NaN 
        }
    }
    
    fun onCubeRootClick() {
        applyUnaryOperation { calculatorEngine.cubeRoot(it) }
    }
    
    fun onSinClick() {
        applyUnaryOperation { calculatorEngine.sin(it, isDegrees) }
    }
    
    fun onCosClick() {
        applyUnaryOperation { calculatorEngine.cos(it, isDegrees) }
    }
    
    fun onTanClick() {
        applyUnaryOperation { calculatorEngine.tan(it, isDegrees) }
    }
    
    fun onLogClick() {
        applyUnaryOperation { calculatorEngine.log10(it).getOrNull() ?: Double.NaN }
    }
    
    fun onLnClick() {
        applyUnaryOperation { calculatorEngine.ln(it).getOrNull() ?: Double.NaN }
    }
    
    fun onPiClick() {
        currentNumber = calculatorEngine.getPi().toString().take(10)
        isNewNumber = true
        updateDisplay()
    }
    
    fun onEClick() {
        currentNumber = calculatorEngine.getE().toString().take(10)
        isNewNumber = true
        updateDisplay()
    }
    
    fun onFactorialClick() {
        val value = currentNumber.toDoubleOrNull()?.toInt() ?: return
        when (val result = calculatorEngine.factorial(value)) {
            is Result.Success -> {
                currentNumber = result.getOrNull().toString()
                updateDisplay()
            }
            is Result.Failure -> {
                _uiState.value = _uiState.value.copy(
                    display = "Error",
                    isError = true
                )
            }
            else -> {}
        }
    }
    
    fun onPowerClick() {
        onOperatorClick("^")
    }
    
    fun onParenthesisClick(open: Boolean) {
        if (open) {
            currentExpression += "("
            isNewNumber = true
        } else {
            currentExpression += "$currentNumber)"
            currentNumber = "0"
            isNewNumber = true
        }
        updateDisplay()
    }
    
    // Memory operations
    fun onMemoryClear() {
        _uiState.value = _uiState.value.copy(
            memory = CalculatorMemory()
        )
    }
    
    fun onMemoryRecall() {
        if (_uiState.value.memory.isStored) {
            currentNumber = _uiState.value.memory.value.toString()
            isNewNumber = true
            updateDisplay()
        }
    }
    
    fun onMemoryAdd() {
        val value = currentNumber.toDoubleOrNull() ?: return
        val currentMemory = _uiState.value.memory
        _uiState.value = _uiState.value.copy(
            memory = CalculatorMemory(
                value = currentMemory.value + value,
                isStored = true
            )
        )
    }
    
    fun onMemorySubtract() {
        val value = currentNumber.toDoubleOrNull() ?: return
        val currentMemory = _uiState.value.memory
        _uiState.value = _uiState.value.copy(
            memory = CalculatorMemory(
                value = currentMemory.value - value,
                isStored = true
            )
        )
    }
    
    fun onMemoryStore() {
        val value = currentNumber.toDoubleOrNull() ?: return
        _uiState.value = _uiState.value.copy(
            memory = CalculatorMemory(
                value = value,
                isStored = true
            )
        )
    }
    
    // UI actions
    fun toggleMode() {
        _uiState.value = _uiState.value.copy(
            mode = if (_uiState.value.mode == CalculatorMode.BASIC) {
                CalculatorMode.SCIENTIFIC
            } else {
                CalculatorMode.BASIC
            }
        )
    }
    
    fun toggleHistory() {
        _uiState.value = _uiState.value.copy(
            isHistoryVisible = !_uiState.value.isHistoryVisible
        )
    }
    
    fun clearHistory() {
        viewModelScope.launch {
            clearHistoryUseCase()
        }
    }
    
    fun toggleAngleMode() {
        isDegrees = !isDegrees
    }
    
    private fun applyUnaryOperation(operation: (Double) -> Double) {
        if (_uiState.value.isError) return
        
        val value = currentNumber.toDoubleOrNull() ?: return
        val result = operation(value)
        
        currentNumber = if (result.isNaN() || result.isInfinite()) {
            "Error"
        } else {
            formatResult(result)
        }
        isNewNumber = true
        updateDisplay()
    }
    
    private fun formatResult(value: Double): String {
        return when {
            value == value.toLong().toDouble() -> value.toLong().toString()
            else -> {
                val formatted = String.format("%.10f", value).replace(",", ".")
                formatted.trimEnd('0').trimEnd('.')
            }
        }
    }
    
    private fun resetCalculator() {
        currentNumber = "0"
        currentExpression = ""
        lastOperation = ""
        isNewNumber = true
        _uiState.value = CalculatorUiState(
            mode = _uiState.value.mode,
            memory = _uiState.value.memory,
            history = _uiState.value.history
        )
    }
    
    private fun updateDisplay() {
        _uiState.value = _uiState.value.copy(
            display = currentNumber,
            expression = currentExpression,
            isError = false
        )
    }
}
