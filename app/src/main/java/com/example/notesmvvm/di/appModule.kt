package com.example.notesmvvm.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesmvvm.data.TodoDatabase
import com.example.notesmvvm.data.TodoRepository
import com.example.notesmvvm.data.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object appModule {

    @Provides
    @Singleton
    fun providesTodoDatabase(app:Application):TodoDatabase{
        return Room.databaseBuilder(app,
            TodoDatabase::class.java,
            "todo_db"
            ).build()
    }


    @Provides
    @Singleton
    fun provideTodoRepository(db:TodoDatabase):TodoRepository{
        return TodoRepositoryImpl(db.dao)
    }

}