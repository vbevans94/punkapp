package com.punkapp.ui.beers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.punkapp.beers.Beer
import com.punkapp.beers.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val repository: BeerRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun fetchBeers() = viewModelScope.launch {
        val result: Result<List<Beer>> = repository.getBeers()
        if (result.isSuccess) {
            _state.update {
                State(beers = result.getOrDefault(emptyList()))
            }
        } else {
            _state.update {
                State(isError = true)
            }
        }
    }

    data class State(
        val beers: List<Beer> = emptyList(),
        val isError: Boolean = false
    )
}