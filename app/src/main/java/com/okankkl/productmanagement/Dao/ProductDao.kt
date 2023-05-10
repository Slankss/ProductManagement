package com.okankkl.productmanagement.Dao

import androidx.core.location.LocationRequestCompat.Quality
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.okankkl.productmanagement.Model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {


    @Query("SELECT * FROM Product ORDER BY p_id ASC")
    fun readAll() : List<Product>

    @Upsert
    suspend fun addProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM Product WHERE p_name LIKE '%' || :search || '%'")
    fun search(search : String) : List<Product>

    @Query("SELECT * FROM Product ORDER BY p_name ASC")
    fun readAllOrderName() : List<Product>

    @Query("SELECT * FROM Product ORDER BY p_price ASC")
    fun readAllOrderPrice() : List<Product>


}