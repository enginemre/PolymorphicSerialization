package com.engin.polymorphicserialization.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engin.polymorphicserialization.data.dto.Section
import com.engin.polymorphicserialization.domain.GetSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSectionsUseCase: GetSectionsUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()
    private val _sectionList = MutableStateFlow<List<Section>>(emptyList())
    val sectionList = _sectionList.asStateFlow()

    fun getSections() {
        getSectionsUseCase().onStart {
            _isLoading.emit(true)
        }.catch {
            _error.send(it.message ?: "Error")
        }.onEach {
            _sectionList.update { it }
        }.onCompletion {
            _isLoading.emit(false)
        }.launchIn(viewModelScope)
    }
}