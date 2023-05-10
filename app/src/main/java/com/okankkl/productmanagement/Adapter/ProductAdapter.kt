package com.okankkl.productmanagement.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.okankkl.productmanagement.Model.Product
import com.okankkl.productmanagement.databinding.ProductRowBinding

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.Holder>() {

    var productList = emptyList<Product>()
    var deleteClick :(Product) -> Unit = {}
    var editClick : (Product) -> Unit = {}

    fun setData(productList : List<Product>){
        this.productList = productList
    }

    class Holder(val binding : ProductRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductRowBinding.inflate(layoutInflater,parent,false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        var current = productList[position]
        holder.binding.apply {
            txtProductId.text = current.p_id.toString()
            txtProductName.text = current.p_name
            txtProductPrice.text = "${current.p_price} ₺"

            btnDelete.setOnClickListener {
                deleteClick(current)
            }

            btnEdit.setOnClickListener{
                editClick(current)
            }

        }



    }
}