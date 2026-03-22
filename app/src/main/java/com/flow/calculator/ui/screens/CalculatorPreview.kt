package com.flow.calculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flow.calculator.domain.calculator.CalculatorMode
import com.flow.calculator.ui.components.CalculatorButton
import com.flow.calculator.ui.components.CalculatorDisplay
import com.flow.calculator.ui.components.HistoryPanel
import com.flow.calculator.ui.components.ScientificButton
import com.flow.calculator.ui.components.WideCalculatorButton
import com.flow.calculator.ui.theme.CalculatorTheme

/**
 * Preview del display de la calculadora
 */
@Preview(name = "Display - Light", showBackground = true)
@Composable
fun DisplayPreviewLight() {
    CalculatorTheme(darkTheme = false) {
        Surface {
            CalculatorDisplay(
                expression = "1250 + 500 × 2",
                result = "2250",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(name = "Display - Dark", showBackground = true)
@Composable
fun DisplayPreviewDark() {
    CalculatorTheme(darkTheme = true) {
        Surface {
            CalculatorDisplay(
                expression = "1250 + 500 × 2",
                result = "2250",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

/**
 * Preview de botones básicos
 */
@Preview(name = "Basic Buttons - Light")
@Composable
fun BasicButtonsPreviewLight() {
    CalculatorTheme(darkTheme = false) {
        Surface {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CalculatorButton(
                        text = "C",
                        onClick = {},
                        backgroundColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                    CalculatorButton(text = "7", onClick = {})
                    CalculatorButton(text = "8", onClick = {})
                    CalculatorButton(text = "9", onClick = {})
                    CalculatorButton(
                        text = "÷",
                        onClick = {},
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CalculatorButton(text = "4", onClick = {})
                    CalculatorButton(text = "5", onClick = {})
                    CalculatorButton(text = "6", onClick = {})
                    CalculatorButton(
                        text = "×",
                        onClick = {},
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}

/**
 * Preview de botones científicos
 */
@Preview(name = "Scientific Buttons")
@Composable
fun ScientificButtonsPreview() {
    CalculatorTheme(darkTheme = false) {
        Surface {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    ScientificButton(text = "sin", onClick = {})
                    ScientificButton(text = "cos", onClick = {})
                    ScientificButton(text = "tan", onClick = {})
                    ScientificButton(text = "log", onClick = {})
                    ScientificButton(text = "ln", onClick = {})
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    ScientificButton(text = "x²", onClick = {})
                    ScientificButton(text = "x³", onClick = {})
                    ScientificButton(text = "√", onClick = {})
                    ScientificButton(text = "∛", onClick = {})
                    ScientificButton(text = "x^y", onClick = {})
                }
            }
        }
    }
}

/**
 * Preview completo - Modo Básico
 */
@Preview(name = "Full Basic Mode - Light", widthDp = 360, heightDp = 640)
@Composable
fun FullBasicModePreviewLight() {
    CalculatorTheme(darkTheme = false) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Calculator", style = MaterialTheme.typography.titleLarge)
                    Row {
                        IconButton(onClick = {}) { Text("🔬") }
                        IconButton(onClick = {}) { Text("📜") }
                    }
                }
                
                CalculatorDisplay(
                    expression = "1250 + 500 × 2",
                    result = "2250",
                    modifier = Modifier.weight(1f)
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    BasicCalculatorKeyboard(
                        onDigitClick = {},
                        onOperatorClick = {},
                        onDecimalClick = {},
                        onEqualsClick = {},
                        onClearClick = {},
                        onClearEntryClick = {},
                        onPercentClick = {},
                        onNegateClick = {}
                    )
                }
            }
        }
    }
}

@Preview(name = "Full Basic Mode - Dark", widthDp = 360, heightDp = 640)
@Composable
fun FullBasicModePreviewDark() {
    CalculatorTheme(darkTheme = true) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Calculator", style = MaterialTheme.typography.titleLarge)
                    Row {
                        IconButton(onClick = {}) { Text("🔬") }
                        IconButton(onClick = {}) { Text("📜") }
                    }
                }
                
                CalculatorDisplay(
                    expression = "1250 + 500 × 2",
                    result = "2250",
                    modifier = Modifier.weight(1f)
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    BasicCalculatorKeyboard(
                        onDigitClick = {},
                        onOperatorClick = {},
                        onDecimalClick = {},
                        onEqualsClick = {},
                        onClearClick = {},
                        onClearEntryClick = {},
                        onPercentClick = {},
                        onNegateClick = {}
                    )
                }
            }
        }
    }
}

/**
 * Preview del panel de historial
 */
@Preview(name = "History Panel")
@Composable
fun HistoryPanelPreview() {
    CalculatorTheme(darkTheme = false) {
        Surface {
            HistoryPanel(
                history = listOf(
                    "1250 + 500 = 1750",
                    "√144 = 12",
                    "sin(30) = 0.5",
                    "25 × 4 = 100",
                    "100 ÷ 4 = 25"
                ),
                onDismiss = {},
                onClear = {}
            )
        }
    }
}
