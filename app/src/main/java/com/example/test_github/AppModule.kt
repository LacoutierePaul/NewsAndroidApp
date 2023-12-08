package com.example.test_github

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.example.test_github.Utils.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager {
        return context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    }

}


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideArticleDatabase(
        @ApplicationContext context: Context
    ): ArticleDatabase {

        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "articles.db"
        ).fallbackToDestructiveMigration().build() //retirer allowMainThreadQueries et fallbackToDestructiveMigration une dev fini
    }

}