package com.example.todotasker.feature_main_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.feature_main_screen.domain.model.Task
import com.example.todotasker.feature_main_screen.domain.use_cases.MainScreenUseCases
import com.example.todotasker.feature_main_screen.presentation.utils.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainScreenViewModel : ViewModel() {

    private val useCases = MainScreenUseCases()

    private val _colors: MutableLiveData<MutableMap<Int, Int>> = MutableLiveData()
    val colors: LiveData<MutableMap<Int, Int>> = _colors

    private val _tasks: MutableLiveData<List<Task>> = MutableLiveData()
    val tasks: LiveData<List<Task>> = _tasks

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData()
    val notes: LiveData<List<Note>> = _notes

    private val eventChannel = Channel<UiEvent>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    private val _selectedTask: MutableLiveData<Task> = MutableLiveData()
    val selectedTask: LiveData<Task> = _selectedTask

    private val dispatcher = Dispatchers.IO

    init {
        getTasks()
    }

    fun taskClicked(task: Task) {
        viewModelScope.launch(dispatcher) {
            _selectedTask.postValue(task)
            eventChannel.send(UiEvent.ShowTaskDescription(getRandomFloat()))
        }
    }

    fun newTaskClicked() {
        viewModelScope.launch(dispatcher) {
            eventChannel.send(UiEvent.ShowNewTaskAlert)
        }
    }

    fun saveNewTask(task: Task) {
        viewModelScope.launch(dispatcher) {
            useCases.saveTask(task)
            getTasks()
        }
    }

    fun removeTaskClicked() {
        viewModelScope.launch(dispatcher) {
            eventChannel.send(UiEvent.HideTaskDescription(getRandomFloat()))
            useCases.deleteTask(_selectedTask.value!!)
        }
    }

    fun getTasks() {
        viewModelScope.launch(dispatcher) {
            val result = useCases.getTasks()
            _tasks.postValue(result)
        }
    }

    // Этот Random нужен тупо чтобы триггерить LaunchedEffect
    private fun getRandomFloat() = Random.nextFloat()
}