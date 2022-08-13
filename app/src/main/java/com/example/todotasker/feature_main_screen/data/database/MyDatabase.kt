package com.example.todotasker.feature_main_screen.data.database

import androidx.room.Room
import com.example.todotasker.App


class MyDatabase private constructor(){
    companion object {
        private var instance: ToDoDatabase? = null

        fun getInstance(): ToDoDatabase{
            if (instance == null){
                instance = Room
                    .databaseBuilder(App.getContext(), ToDoDatabase::class.java, "todo_tasker")
                    .build()
            }
            return instance!!
        }
    }
}