package com.flow.calculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.History
import androidx.compose.material.icons.automirrored.filled.HistoryToggleOff
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.flow.calculator.domain.calculator.CalculatorMode
import com.flow.calculator.ui.components.CalculatorDisplay
import com.flow.calculator.ui.components.HistoryPanel
import com.flow.calculator.ui.viewmodel.CalculatorViewModel

/**
 * Main calculator screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculator") },
                actions = {
                    // Mode toggle button
                    IconButton(onClick = { viewModel.toggleMode() }) {
                        Icon(
                            imageVector = if (uiState.mode == CalculatorMode.SCIENTIFIC) {
                                Icons.Default.Smartphone
                            } else {
                                Icons.Default.Science
                            },
                            contentDescription = if (uiState.mode == CalculatorMode.SCIENTIFIC) {
                                "Switch to Basic Mode"
                            } else {
                                "Switch to Scientific Mode"
                            }
                        )
                    }
                    // History toggle button
                    IconButton(onClick = { viewModel.toggleHistory() }) {
                        Icon(
                            imageVector = if (uiState.isHistoryVisible) {
                                Icons.AutoMirrored.Filled.HistoryToggleOff
                            } else {
                                Icons.AutoMirrored.Filled.History
                            },
                            contentDescription = if (uiState.isHistoryVisible) {
                                "Hide History"
                            } else {
                                "Show History"
                            }
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            // History Panel
            if (uiState.isHistoryVisible) {
                HistoryPanel(
                    history = uiState.history,
                    onDismiss = { viewModel.toggleHistory() },
                    onClear = { viewModel.clearHistory() },
                    modifier = Modifier.weight(0.3f)
                )
            }
            
            // Display
            CalculatorDisplay(
                expression = uiState.expression,
                result = uiState.display,
                isError = uiState.isError,
                modifier = Modifier.weight(0.3f)
            )
            
            // Keyboard
            Box(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth()
            ) {
                if (uiState.mode == CalculatorMode.BASIC) {
                    BasicCalculatorKeyboard(
                        onDigitClick = viewModel::onDigitClick,
                        onOperatorClick = viewModel::onOperatorClick,
                        onDecimalClick = viewModel::onDecimalClick,
                        onEqualsClick = viewModel::onEqualsClick,
                        onClearClick = viewModel::onClearClick,
                        onClearEntryClick = viewModel::onClearEntryClick,
                        onPercentClick = viewModel::onPercentClick,
                        onNegateClick = viewModel::onNegateClick
                    )
                } else {
                    ScientificCalculatorKeyboard(
                        onDigitClick = viewModel::onDigitClick,
                        onOperatorClick = viewModel::onOperatorClick,
                        onDecimalClick = viewModel::onDecimalClick,
                        onEqualsClick = viewModel::onEqualsClick,
                        onClearClick = viewModel::onClearClick,
                        onClearEntryClick = viewModel::onClearEntryClick,
                        onPercentClick = viewModel::onPercentClick,
                        onNegateClick = viewModel::onNegateClick,
                        onSinClick = viewModel::onSinClick,
                        onCosClick = viewModel::onCosClick,
                        onTanClick = viewModel::onTanClick,
                        onLogClick = viewModel::onLogClick,
                        onLnClick = viewModel::onLnClick,
                        onSquareClick = viewModel::onSquareClick,
                        onCubeClick = viewModel::onCubeClick,
                        onSquareRootClick = viewModel::onSquareRootClick,
                        onCubeRootClick = viewModel::onCubeRootClick,
                        onPowerClick = viewModel::onPowerClick,
                        onFactorialClick = viewModel::onFactorialClick,
                        onPiClick = viewModel::onPiClick,
                        onEClick = viewModel::onEClick,
                        onParenthesisClick = viewModel::onParenthesisClick,
                        onMemoryClear = viewModel::onMemoryClear,
                        onMemoryRecall = viewModel::onMemoryRecall,
                        onMemoryAdd = viewModel::onMemoryAdd,
                        onMemorySubtract = viewModel::onMemorySubtract,
                        onMemoryStore = viewModel::onMemoryStore
                    )
                }
            }
        }
    }
}
