package me.floow.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.floow.database.dbo.TestDbo

@Database(entities = [TestDbo::class], version = 1)
abstract class AppDatabase() : RoomDatabase() {

}