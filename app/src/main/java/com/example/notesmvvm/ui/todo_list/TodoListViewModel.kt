package com.example.notesmvvm.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesmvvm.data.Todo
import com.example.notesmvvm.data.TodoRepository
import com.example.notesmvvm.util.Routes
import com.example.notesmvvm.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val todos = repository.getTodos();


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deleteTodo: Todo? = null

    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.OnTodoClick -> {
                sendUIEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }

            is TodoListEvent.OnAddTodoClick -> {
                sendUIEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }

            is TodoListEvent.DeleteTodo -> {
                viewModelScope.launch {
                    deleteTodo = event.todo
                    repository.deleteTodo(event.todo)
                    sendUIEvent(UiEvent.ShowSnackBar("Todo Deleted","Undo"))
                }
            }

            is TodoListEvent.OnUndoDeleteClick -> {
                deleteTodo?.let {
                    viewModelScope.launch {
                        repository.insertTodo(it)
                    }
                }
            }

            is TodoListEvent.OnDoneChange -> {
                 viewModelScope.launch {
                     repository.insertTodo(event.todo.copy(
                         isDone = event.isDone
                     ))
                 }
            }
        }
    }


    private fun sendUIEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}