package com.maf.receiptmachine.service

import com.maf.receiptmachine.model.Product
import com.maf.receiptmachine.model.SelectedItemDetails

interface CartService {
    @Throws(NoSuchElementException::class)
    fun processAndAddItemToTheCart(selectedItemDetails: SelectedItemDetails)

    fun getProductDetails(productId: Long): Product?
    fun calculateTaxAmount(product: Product): Double
    fun checkOutCartItems()
}