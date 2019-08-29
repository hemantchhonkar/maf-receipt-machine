package com.maf.receiptmachine.subscriber

import com.maf.receiptmachine.model.SelectedItemDetails
import com.maf.receiptmachine.service.CartService
import com.maf.receiptmachine.service.CartServiceImpl

class CartSubscriber : GenericSubscriber {

    val cartService: CartService

    init {
        cartService = CartServiceImpl()
    }

    override fun processAllTheItems() {
        this.cartService.checkOutCartItems()
    }

    override fun processSelectedItem(selectedItemDetails: SelectedItemDetails) {
        this.cartService.processAndAddItemToTheCart(selectedItemDetails)
    }
}