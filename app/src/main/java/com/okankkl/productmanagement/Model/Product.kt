package com.okankkl.productmanagement.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class Product(

    var p_name : String,
    var p_price : Double,
    @PrimaryKey(autoGenerate = true)
    var p_id : Int = 0

)
