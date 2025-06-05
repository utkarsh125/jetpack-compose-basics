package com.example.counterapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

//Inherit from ViewModel
class CounterViewModel: ViewModel() {

    //whenever you make a variable private then use the underscore to that variable (common practice)

    //private val _count = mutableStateOf(0)

    private val _repository: CounterRepository = CounterRepository()

    private val _count = mutableStateOf(_repository.getCounter().count)
    //expose the count as an immutable state
    val count: MutableState<Int> = _count

    fun increment () {
        _repository.incrementCounter()
        _count.value = _repository.getCounter().count
    }

    fun decrement () {
        _repository.decrementCounter()
        _count.value = _repository.getCounter().count
    }

}