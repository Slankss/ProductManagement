package com.okankkl.productmanagement

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.okankkl.productmanagement.Model.Product
import com.okankkl.productmanagement.databinding.ProductAddBottomSheetBinding

class BottomFragment() : BottomSheetDialogFragment() {

    private var product : Product? = null
    private var _binding : ProductAddBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : ProductViewModel

    var product_name = ""
    var product_price = 0.0

    fun setProduct(product : Product?){
        this.product = product
        if(product != null){
            product_name = product.p_name
            product_price = product.p_price
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProductAddBottomSheetBinding.inflate(layoutInflater,container,false)

        activity?.let {
            viewModel = ProductViewModel(it.application)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       binding.btnAddProduct.setOnClickListener {
           product_name = binding.edtName.text.toString()
           product_price = binding.edtPrice.text.toString().toDouble()

           if(!product_name.isBlank() && product_price != 0.0){
               if(product != null) {
                   product!!.p_name = product_name
                   product!!.p_price = product_price
               }
               var newProduct = when(product){
                   null -> Product(product_name,product_price)
                   else -> {
                       product
                   }
               }
                binding.edtName.text?.clear()
                binding.edtPrice.text?.clear()
               viewModel.AddProduct(newProduct!!)
           }


       }


    }

    override fun onPause() {
        super.onPause()
        binding.edtName.text?.clear()
        binding.edtPrice.text?.clear()
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}