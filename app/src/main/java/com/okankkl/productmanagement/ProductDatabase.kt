package com.okankkl.productmanagement

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.okankkl.productmanagement.Dao.ProductDao
import com.okankkl.productmanagement.Model.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao() : ProductDao

    companion object{

        private var INSTANCE : ProductDatabase? = null

        fun getDatabase(context : Context) : ProductDatabase {

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){

                val instance = Room.databaseBuilder(
                    context,
                    ProductDatabase::class.java,
                    "product_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }



}