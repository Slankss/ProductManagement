package com.okankkl.productmanagement

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okankkl.productmanagement.Model.Product
import com.okankkl.productmanagement.Model.Product_Column
import com.okankkl.productmanagement.Repository.ProductRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(context : Context) : ViewModel() {

    private lateinit var repository : ProductRepository
    var productList = MutableLiveData<List<Product>>()
    var sort_type = Product_Column.p_id.name


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val productDao = ProductDatabase.getDatabase(context).productDao()
            repository = ProductRepository(productDao)
            productList.postValue(repository.getAllData())
        }
    }


    fun AddProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
            getProducts()
        }
    }

    fun DeleteProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProduct(product)
        }
    }

    fun getProducts(){
        viewModelScope.launch(Dispatchers.IO){
            productList.postValue(repository.getAllData())
        }

    }

    fun getProductsOrderName(){
        viewModelScope.launch(Dispatchers.IO){
            productList.postValue(repository.getAllDataOrderName())
        }

    }

    fun getProductsOrderPrice(){
        viewModelScope.launch(Dispatchers.IO){
            productList.postValue(repository.getAllDataOrderPrice())
        }
    }


    fun search(search : String) {
        viewModelScope.launch(Dispatchers.IO){
            productList.postValue(repository.search(search))
        }
    }




}

