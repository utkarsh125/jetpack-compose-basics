package com.example.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table") //database name
data class Wish(
    //primary key
    @PrimaryKey(autoGenerate = true) //automatically increment IDs such that two of them cannot be same
    val id: Long = 0L,

    @ColumnInfo(name = "wish-title") //define column name for title
    val title: String = "",

    @ColumnInfo(name = "wish-desc") //define column name for description
    val description: String = ""
)
//adding dummyData for now
//later will connect with roomDB
object DummyWish {
    val wishList = listOf(
        Wish(title = "Sony ZVE10 M2", description = "A camera"),
        Wish(title = "ViewSonic 2K Monitor", description = "A monitor"),
        Wish(title = "Samsung S25 ", description = "A phone"),
        Wish(title = "Shure MK1", description = "A microphone"),
    )
}
