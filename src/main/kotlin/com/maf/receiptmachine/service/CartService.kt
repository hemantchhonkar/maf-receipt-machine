package com.maf.receiptmachine.service

import com.maf.receiptmachine.model.Category
import com.maf.receiptmachine.model.Product
import com.maf.receiptmachine.model.SelectedItemDetails
import com.maf.receiptmachine.model.Store
import com.maf.receiptmachine.model.cart.Cart
import java.lang.RuntimeException

class CartService {
    val store = Store
    val cart = Cart()

    @Throws(NoSuchElementException::class)
    fun processAndAddItemToTheCart(selectedItemDetails: SelectedItemDetails) {
        val productFromDB = this.getProductDetails(selectedItemDetails.productId)
        if(productFromDB == null ){
            throw NoSuchElementException("Invalid Product")
        }

    }

    fun getProductDetails(productId: Long): Product? {
        return store.productList.filter { product -> product.id == productId }.getOrNull(0)
    }

    fun getTaxForGivenCategory(category: Category): Double {
        return store.categoryTaxMap.getOrElse(category, { 0.0 })
    }
}