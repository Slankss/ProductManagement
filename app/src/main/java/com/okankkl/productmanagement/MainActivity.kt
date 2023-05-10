package com.okankkl.productmanagement

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.okankkl.productmanagement.Adapter.ProductAdapter
import com.okankkl.productmanagement.Model.Product
import com.okankkl.productmanagement.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var adapter : ProductAdapter
    private lateinit var viewModel : ProductViewModel
    private var productList = emptyList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ProductViewModel(applicationContext)
        var bottomFragment = BottomFragment()

        val layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.layoutManager = layoutManager
        adapter = ProductAdapter()
        adapter.setData(productList)
        binding.recyclerView.adapter = adapter

        binding.imgUpOrDown.setOnClickListener {
            var cardVisibility = binding.expandableLayout.isVisible
            if(!cardVisibility){
                TransitionManager.beginDelayedTransition(binding.topBar,AutoTransition())
                binding.expandableLayout.visibility = View.VISIBLE
                binding.imgUpOrDown.setImageResource(R.drawable.ic_up)
            }
            else{
                TransitionManager.beginDelayedTransition(binding.topBar,AutoTransition())
                binding.expandableLayout.visibility = View.GONE
                binding.imgUpOrDown.setImageResource(R.drawable.ic_down)
            }
        }

        binding.searchTextInputEditText.doOnTextChanged { text, start, before, count ->
            if(text != null && text.isNotBlank()){
                viewModel.search(text.toString())
            }
            else{
                viewModel.getProducts()
            }
        }

        adapter.deleteClick = {
            viewModel.DeleteProduct(it)
        }

        adapter.editClick = {
            bottomFragment.setProduct(it)
            bottomFragment.show(supportFragmentManager,"TAG")
        }


        binding.btnAdd.setOnClickListener {
            bottomFragment.setProduct(null)
            bottomFragment.show(supportFragmentManager,"TAG")
        }


        viewModel.productList.observe(this,Observer{
            productList = it
            adapter.setData(productList)
            adapter.notifyDataSetChanged()
        })

        binding.radioId.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                viewModel.getProducts()
            }
        }

        binding.radioName.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                viewModel.getProductsOrderName()
            }
        }

        binding.radioPrice.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                viewModel.getProductsOrderPrice()
            }
        }

        /*
        binding.txtSearchEditText.doOnTextChanged { text, start, before, count ->
            viewModel.search(text.toString())
            if(text != null && text.trim().isNotEmpty()){
                viewModel.search(text.toString())
            }
            else{
                viewModel.getProducts()
            }
        }

         */


    }

}