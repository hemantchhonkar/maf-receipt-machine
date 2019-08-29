package com.maf.receiptmachine.service

import com.maf.receiptmachine.model.Product
import com.maf.receiptmachine.model.SelectedItemDetails
import com.maf.receiptmachine.model.Store
import com.maf.receiptmachine.model.cart.Cart
import com.maf.receiptmachine.model.cart.CartItem
import com.maf.receiptmachine.util.roundDouble

class CartServiceImpl : CartService {

    private val store = Store
    val cart = Cart()
    private val taxService = TaxServiceImpl()

    @Throws(NoSuchElementException::class)
    override fun processAndAddItemToTheCart(selectedItemDetails: SelectedItemDetails) {
        val productFromDB =
            this.getProductDetails(selectedItemDetails.productId) ?: throw NoSuchElementException("Invalid Product")

        cart.addItem(
            prepareCartItem(productFromDB, selectedItemDetails),
            calculateTaxAmount(productFromDB)
        )

    }

    fun prepareCartItem(
        productFromDB: Product,
        selectedItemDetails: SelectedItemDetails
    ): CartItem {
        return CartItem(
            productFromDB.id, selectedItemDetails.quantity,
            productFromDB.name,
            calculateFinalPrice(productFromDB.price, selectedItemDetails.quantity)
        )
    }

    fun calculateFinalPrice(productPrice: Double, selectedQuantity: Int): Double {
        return productPrice * selectedQuantity
    }

    override fun getProductDetails(productId: Long): Product? {
        return store.productList.filter { product ->
            product.id == productId
        }.getOrNull(0)
    }

    override fun calculateTaxAmount(product: Product): Double {
        return roundDouble(this.taxService.getTaxAmount(product))
    }

    override fun checkOutCartItems() {
        cart.checkout()
    }
}