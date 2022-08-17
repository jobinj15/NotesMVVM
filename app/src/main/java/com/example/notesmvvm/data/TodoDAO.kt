package com.example.notesmvvm.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(toto: Todo)

    @Delete
    suspend fun deleteTodo(toto: Todo)

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id:Int) : Todo?

    @Query("SELECT * from todo")
    fun getTodos(): Flow<List<Todo>>

}