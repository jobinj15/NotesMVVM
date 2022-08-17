package com.example.notesmvvm.data
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(toto: Todo)

    suspend fun deleteTodo(toto: Todo)

    suspend fun getTodoById(id:Int) : Todo?

    fun getTodos(): Flow<List<Todo>>

}