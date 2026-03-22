package com.flow.calculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flow.calculator.ui.components.CalculatorButton
import com.flow.calculator.ui.components.ScientificButton
import com.flow.calculator.ui.components.WideCalculatorButton
import com.flow.calculator.ui.viewmodel.CalculatorViewModel

/**
 * Basic calculator keyboard
 */
@Composable
fun BasicCalculatorKeyboard(
    onDigitClick: (String) -> Unit,
    onOperatorClick: (String) -> Unit,
    onDecimalClick: () -> Unit,
    onEqualsClick: () -> Unit,
    onClearClick: () -> Unit,
    onClearEntryClick: () -> Unit,
    onPercentClick: () -> Unit,
    onNegateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Row 1: C, CE, %, ÷
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorButton(
                text = "C",
                onClick = onClearClick,
                backgroundColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
            CalculatorButton(
                text = "CE",
                onClick = onClearEntryClick,
                backgroundColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.7f),
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
            CalculatorButton(
                text = "%",
                onClick = onPercentClick
            )
            CalculatorButton(
                text = "÷",
                onClick = { onOperatorClick("/") },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        
        // Row 2: 7, 8, 9, ×
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorButton(text = "7", onClick = { onDigitClick("7") })
            CalculatorButton(text = "8", onClick = { onDigitClick("8") })
            CalculatorButton(text = "9", onClick = { onDigitClick("9") })
            CalculatorButton(
                text = "×",
                onClick = { onOperatorClick("*") },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        
        // Row 3: 4, 5, 6, -
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorButton(text = "4", onClick = { onDigitClick("4") })
            CalculatorButton(text = "5", onClick = { onDigitClick("5") })
            CalculatorButton(text = "6", onClick = { onDigitClick("6") })
            CalculatorButton(
                text = "-",
                onClick = { onOperatorClick("-") },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        
        // Row 4: 1, 2, 3, +
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorButton(text = "1", onClick = { onDigitClick("1") })
            CalculatorButton(text = "2", onClick = { onDigitClick("2") })
            CalculatorButton(text = "3", onClick = { onDigitClick("3") })
            CalculatorButton(
                text = "+",
                onClick = { onOperatorClick("+") },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        
        // Row 5: +/-, 0, ., =
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorButton(text = "+/-", onClick = onNegateClick)
            WideCalculatorButton(
                text = "0",
                onClick = { onDigitClick("0") },
                modifier = Modifier.weight(2f)
            )
            CalculatorButton(text = ".", onClick = onDecimalClick)
            CalculatorButton(
                text = "=",
                onClick = onEqualsClick,
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

/**
 * Scientific calculator keyboard
 */
@Composable
fun ScientificCalculatorKeyboard(
    onDigitClick: (String) -> Unit,
    onOperatorClick: (String) -> Unit,
    onDecimalClick: () -> Unit,
    onEqualsClick: () -> Unit,
    onClearClick: () -> Unit,
    onClearEntryClick: () -> Unit,
    onPercentClick: () -> Unit,
    onNegateClick: () -> Unit,
    onSinClick: () -> Unit,
    onCosClick: () -> Unit,
    onTanClick: () -> Unit,
    onLogClick: () -> Unit,
    onLnClick: () -> Unit,
    onSquareClick: () -> Unit,
    onCubeClick: () -> Unit,
    onSquareRootClick: () -> Unit,
    onCubeRootClick: () -> Unit,
    onPowerClick: () -> Unit,
    onFactorialClick: () -> Unit,
    onPiClick: () -> Unit,
    onEClick: () -> Unit,
    onParenthesisClick: (Boolean) -> Unit,
    onMemoryClear: () -> Unit,
    onMemoryRecall: () -> Unit,
    onMemoryAdd: () -> Unit,
    onMemorySubtract: () -> Unit,
    onMemoryStore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        // Memory row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            ScientificButton(text = "MC", onClick = onMemoryClear, modifier = Modifier.weight(1f))
            ScientificButton(text = "MR", onClick = onMemoryRecall, modifier = Modifier.weight(1f))
            ScientificButton(text = "M+", onClick = onMemoryAdd, modifier = Modifier.weight(1f))
            ScientificButton(text = "M-", onClick = onMemorySubtract, modifier = Modifier.weight(1f))
            ScientificButton(text = "MS", onClick = onMemoryStore, modifier = Modifier.weight(1f))
        }
        
        // Scientific row 1
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            ScientificButton(text = "sin", onClick = onSinClick, modifier = Modifier.weight(1f))
            ScientificButton(text = "cos", onClick = onCosClick, modifier = Modifier.weight(1f))
            ScientificButton(text = "tan", onClick = onTanClick, modifier = Modifier.weight(1f))
            ScientificButton(text = "log", onClick = onLogClick, modifier = Modifier.weight(1f))
            ScientificButton(text = "ln", onClick = onLnClick, modifier = Modifier.weight(1f))
        }
        
        // Scientific row 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            ScientificButton(text = "x²", onClick = onSquareClick, modifier = Modifier.weight(1f))
            ScientificButton(text = "x³", onClick = onCubeClick, modifier = Modifier.weight(1f))
            ScientificButton(text = "√", onClick = onSquareRootClick, modifier = Modifier.weight(1f))
            ScientificButton(text = "∛", onClick = onCubeRootClick, modifier = Modifier.weight(1f))
            ScientificButton(text = "x^y", onClick = onPowerClick, modifier = Modifier.weight(1f))
        }
        
        // Scientific row 3
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            ScientificButton(text = "(", onClick = { onParenthesisClick(true) }, modifier = Modifier.weight(1f))
            ScientificButton(text = ")", onClick = { onParenthesisClick(false) }, modifier = Modifier.weight(1f))
            ScientificButton(text = "n!", onClick = onFactorialClick, modifier = Modifier.weight(1f))
            ScientificButton(text = "π", onClick = onPiClick, modifier = Modifier.weight(1f))
            ScientificButton(text = "e", onClick = onEClick, modifier = Modifier.weight(1f))
        }
        
        // Basic calculator section
        Spacer(modifier = Modifier.height(8.dp))
        
        // Row 1: C, CE, %, ÷
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorButton(
                text = "C",
                onClick = onClearClick,
                backgroundColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = "CE",
                onClick = onClearEntryClick,
                backgroundColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.7f),
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = "%",
                onClick = onPercentClick,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = "÷",
                onClick = { onOperatorClick("/") },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
        
        // Row 2: 7, 8, 9, ×
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorButton(text = "7", onClick = { onDigitClick("7") }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "8", onClick = { onDigitClick("8") }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "9", onClick = { onDigitClick("9") }, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = "×",
                onClick = { onOperatorClick("*") },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
        
        // Row 3: 4, 5, 6, -
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorButton(text = "4", onClick = { onDigitClick("4") }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "5", onClick = { onDigitClick("5") }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "6", onClick = { onDigitClick("6") }, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = "-",
                onClick = { onOperatorClick("-") },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
        
        // Row 4: 1, 2, 3, +
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorButton(text = "1", onClick = { onDigitClick("1") }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "2", onClick = { onDigitClick("2") }, modifier = Modifier.weight(1f))
            CalculatorButton(text = "3", onClick = { onDigitClick("3") }, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = "+",
                onClick = { onOperatorClick("+") },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
        
        // Row 5: +/-, 0, ., =
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CalculatorButton(text = "+/-", onClick = onNegateClick, modifier = Modifier.weight(1f))
            WideCalculatorButton(
                text = "0",
                onClick = { onDigitClick("0") },
                modifier = Modifier.weight(2f)
            )
            CalculatorButton(text = ".", onClick = onDecimalClick, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = "=",
                onClick = onEqualsClick,
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
