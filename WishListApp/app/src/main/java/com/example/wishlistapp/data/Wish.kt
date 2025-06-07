package com.example.wishlistapp.data

data class Wish(
    val id: Long = 0L,
    val title: String = "",
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
