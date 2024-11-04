package me.floow.database.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestDbo(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0L,
	val text: String
)
