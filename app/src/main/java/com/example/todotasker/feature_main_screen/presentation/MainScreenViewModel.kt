package com.example.todotasker.feature_main_screen.presentation

import android.util.Log
import androidx.compose.ui.graphics.toArgb
import com.example.todotasker.ui.theme.colors as colorList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.feature_main_screen.domain.model.Task
import kotlin.random.Random

class MainScreenViewModel : ViewModel() {

    private val _colors: MutableLiveData<MutableMap<Int, Int>> = MutableLiveData()
    val colors: LiveData<MutableMap<Int, Int>> = _colors

    private val _tasks: MutableLiveData<List<Task>> = MutableLiveData()
    val tasks: LiveData<List<Task>> = _tasks

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData()
    val notes: LiveData<List<Note>> = _notes

    init {
        fillData()
    }

    private fun fillData(){
        val noteList = mutableListOf<Note>()
        for (i in 1..20){
            noteList.add(
                Note(
                    id = i,
                    title = "Note $i",
                    time = if (i % 3 == 0) "12:$i$i" else null,
                    date = if (i % 5 == 0) "12.$i$i.202$i" else null,
                    isComplete = i % 4 == 0,
                    taskId = Random.nextInt(1, 5)
                )
            )
        }

        val taskList = mutableListOf<Task>()
        for (i in 1..5)
            taskList.add(
                Task (
                    id = i,
                    title = "Task $i",
                    color = colorList.random().toArgb(),
                    notes = noteList.subList(Random.nextInt(1, 10), Random.nextInt(11, 20))
                        )
            )
        val colorList = mutableMapOf<Int, Int>()
        taskList.forEach { task ->
            colorList[task.id] = task.color
        }
        _tasks.value = taskList
        _notes.value = noteList
        _colors.value = colorList

    }


}