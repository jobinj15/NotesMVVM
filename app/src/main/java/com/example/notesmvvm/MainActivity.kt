package com.example.notesmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesmvvm.ui.todo_list.add_edit_todo.AddEditTodoSCreen
import com.example.notesmvvm.ui.todo_list.composables.TodoListScreen
import com.example.notesmvvm.util.Routes
import dagger.hilt.android.AndroidEntryPoint

// Required to inject dependencies into android classes like activity , fragments etc..
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            MaterialTheme{
                val navcontroller = rememberNavController()
                NavHost(navController = navcontroller, startDestination = Routes.TODO_LIST){
                    composable(Routes.TODO_LIST){
                        TodoListScreen(onNavigate = {
                            navcontroller.navigate(it.route)
                        }
                        )
                    }

                    composable(
                        route = Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                        arguments = listOf(
                            navArgument(name="todoId"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){
                        AddEditTodoSCreen(onPopBackStack = {
                            navcontroller.popBackStack()
                        })
                    }
                }
            }
        }
    }
}