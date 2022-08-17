package com.example.notesmvvm.ui.todo_list

import com.example.notesmvvm.data.Todo

open class TodoListEvent {
    data class DeleteTodo(val todo: Todo):TodoListEvent()
    data class OnDoneChange(val todo:Todo, val isDone: Boolean): TodoListEvent()

    //Object will have a single instance , use it in cases where no parameters needed.
    object OnUndoDeleteClick: TodoListEvent()
    data class OnTodoClick(val todo:Todo): TodoListEvent()
    object OnAddTodoClick:TodoListEvent()

}