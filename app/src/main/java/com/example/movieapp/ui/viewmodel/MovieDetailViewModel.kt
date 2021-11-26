package com.example.movieapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.usecase.GetMovieUseCase
import com.example.movieapp.ui.entity.MovieDetailState
import com.example.movieapp.ui.view.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _state = mutableStateOf<MovieDetailState>(MovieDetailState.Loading)
    val state: State<MovieDetailState> = _state

    init {
        savedStateHandle.get<String>(Navigation.MovieDetail.MOVIE)?.let {
            loadMovie(it)
        }
    }

    private fun loadMovie(uid: String) {
        viewModelScope.launch {
            _state.value = MovieDetailState.Content(getMovieUseCase(uid))
        }
    }
}
