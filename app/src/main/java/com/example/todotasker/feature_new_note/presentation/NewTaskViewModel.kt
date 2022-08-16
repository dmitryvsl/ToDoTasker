package com.example.todotasker.feature_new_note.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.feature_main_screen.domain.model.Task
import com.example.todotasker.feature_main_screen.domain.use_cases.MainScreenUseCases
import com.example.todotasker.feature_new_note.domain.use_cases.NewTaskUseCases
import com.example.todotasker.feature_new_note.presentation.utils.InstrumentState
import com.example.todotasker.feature_new_note.presentation.utils.NewTaskUiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class NewTaskViewModel : ViewModel() {
    private val taskUseCases = MainScreenUseCases()
    private val noteUseCases = NewTaskUseCases()

//    private val _text: MutableLiveData<String> = MutableLiveData()
//    val text: LiveData<String> = _text

    private val eventChannel: Channel<NewTaskUiEvent> = Channel(Channel.BUFFERED)
    val eventFlow: Flow<NewTaskUiEvent> = eventChannel.consumeAsFlow()

    private val _taskList: MutableLiveData<List<Task>> = MutableLiveData()
    val taskList: LiveData<List<Task>> = _taskList

    private val _instrumentState: MutableLiveData<InstrumentState> =
        MutableLiveData(InstrumentState.Hidden)
    val instrumentState: LiveData<InstrumentState> = _instrumentState

    private val _note: MutableLiveData<Note> = MutableLiveData(
        Note(
            id = 0,
            title = "",
            time = null,
            date = null,
            isComplete = false,
            taskId = -1
        ))
    val note: LiveData<Note> = _note

    private val dispatcher = Dispatchers.IO

    init {
        getTasks()
    }

    fun onCancelClicked() {
        viewModelScope.launch(dispatcher) {
            eventChannel.send(NewTaskUiEvent.NavigateUp)
        }
    }

    fun onTextChanged(value: String) {
        Log.d("NewTaskViewModel", _note.value?.title.toString())
        _note.postValue(_note.value?.copy(title = value))
    }

    fun onDoneClicked() {
        viewModelScope.launch(dispatcher) {
            if (_note.value?.title?.isNotEmpty() == true) {
                eventChannel.send(NewTaskUiEvent.NavigateUp)
                noteUseCases.saveNote(_note.value!!)
            }
        }
    }

    fun onTaskClicked(task: Task) {
        viewModelScope.launch(dispatcher) {
            _note.postValue(_note.value?.copy(taskId = task.id))
            _instrumentState.postValue(InstrumentState.Hidden)
        }
    }

    fun onTimeSelected(time: String) {
        viewModelScope.launch(dispatcher) {
            _note.postValue(_note.value?.copy(time = time))
        }
    }

    fun handleInstrumentState(instrumentState: InstrumentState) {
        viewModelScope.launch {
            if (_instrumentState.value == InstrumentState.Keyboard && instrumentState != InstrumentState.Keyboard) {
                eventChannel.send(NewTaskUiEvent.HideKeyboard)
                delay(300L)
                eventChannel.send(NewTaskUiEvent.Initial)
            }
            _instrumentState.value = _instrumentState.value!!.handle(instrumentState)
        }
    }

    private fun getTasks() {
        viewModelScope.launch(dispatcher) {
            val result = taskUseCases.getTasks()
            _taskList.postValue(result)
            if (result.isNotEmpty())
                _note.postValue(_note.value?.copy(taskId = result[0].id))
        }
    }


}