package com.example.wishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//an abstract class is a class that has methods that don't need implementation
//they do not have a body that executes
@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addAWish(wishEntity: Wish)

    //what info you want get
    //get wish
    @Query("Select * from `wish-table`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    //update wish will happen in the background
    @Update
    abstract suspend fun updateAWish(wishEntity: Wish)

    //delete entry will happen in the background
    @Delete
    abstract suspend fun deleteAWish(wishEntity: Wish)

    @Query("Select * from `wish-table` where id=:id")
    abstract fun getAWishById(id: Long): Flow<Wish>
}