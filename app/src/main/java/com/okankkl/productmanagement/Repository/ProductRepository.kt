package com.okankkl.productmanagement.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.okankkl.productmanagement.Dao.ProductDao
import com.okankkl.productmanagement.Model.Product
import com.okankkl.productmanagement.Model.Product_Column
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductRepository(private val productDao : ProductDao) {

    fun getAllData() : List<Product>{
        return productDao.readAll()
    }

    fun getAllDataOrderName() : List<Product>{
        return productDao.readAllOrderName()
    }

    fun getAllDataOrderPrice() : List<Product>{
        return productDao.readAllOrderPrice()
    }

    suspend fun addProduct(product: Product){
        productDao.addProduct(product)
    }

    suspend fun deleteProduct(product: Product){
        productDao.deleteProduct(product)
    }


    fun search(search : String) : List<Product> {
        return productDao.search(search)
    }



}