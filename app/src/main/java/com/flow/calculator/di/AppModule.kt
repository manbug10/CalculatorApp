package com.flow.calculator.di

import com.flow.calculator.data.repository.HistoryRepository
import com.flow.calculator.data.repository.impl.HistoryRepositoryImpl
import com.flow.calculator.domain.calculator.CalculatorEngine
import com.flow.calculator.domain.usecase.AddHistoryEntryUseCase
import com.flow.calculator.domain.usecase.ClearHistoryUseCase
import com.flow.calculator.domain.usecase.GetHistoryUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    
    @Binds
    @Singleton
    abstract fun bindHistoryRepository(
        historyRepositoryImpl: HistoryRepositoryImpl
    ): HistoryRepository
}

@Module
@InstallIn(SingletonComponent::class)
object CalculatorModule {
    
    @Provides
    @Singleton
    fun provideCalculatorEngine(): CalculatorEngine {
        return CalculatorEngine()
    }
    
    @Provides
    @Singleton
    fun provideGetHistoryUseCase(repository: HistoryRepository): GetHistoryUseCase {
        return GetHistoryUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideAddHistoryEntryUseCase(repository: HistoryRepository): AddHistoryEntryUseCase {
        return AddHistoryEntryUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideClearHistoryUseCase(repository: HistoryRepository): ClearHistoryUseCase {
        return ClearHistoryUseCase(repository)
    }
}
