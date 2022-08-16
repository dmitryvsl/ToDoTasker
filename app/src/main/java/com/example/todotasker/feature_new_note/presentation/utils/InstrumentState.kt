package com.example.todotasker.feature_new_note.presentation.utils

import android.util.Log

sealed class InstrumentState() {

    abstract fun handle(instrumentState: InstrumentState): InstrumentState

    protected fun compare(oldState: InstrumentState, newState: InstrumentState): InstrumentState {
        // Проверяет если панель инструментов уже открыта
        // Если открыта, возвращает состояние Hidden
        return if (oldState == newState)
            Hidden
        else newState
    }

    object Hidden : InstrumentState() {
        override fun handle(instrumentState: InstrumentState): InstrumentState {
            return this.compare(this, instrumentState)
        }
    }

    object Calendar : InstrumentState() {
        override fun handle(instrumentState: InstrumentState): InstrumentState {
            return this.compare(this, instrumentState)
        }
    }

    object TimePicker : InstrumentState() {
        override fun handle(instrumentState: InstrumentState): InstrumentState {
            return this.compare(this, instrumentState)
        }
    }

    object TaskList : InstrumentState() {
        override fun handle(instrumentState: InstrumentState): InstrumentState {
            return this.compare(this, instrumentState)
        }
    }

    object Keyboard : InstrumentState() {
        override fun handle(instrumentState: InstrumentState): InstrumentState {
            return this.compare(this, instrumentState)
        }
    }
}
