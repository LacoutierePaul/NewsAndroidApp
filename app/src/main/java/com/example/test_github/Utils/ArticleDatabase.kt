package com.example.test_github.Utils

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.test_github.ArticleDB


@Database(
    entities = [ArticleDB::class],
    version = 4
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract val dao: ArticleDAO

}