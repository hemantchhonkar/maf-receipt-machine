package com.maf.receiptmachine.model.cart

data class CartItem (val productId: Long, val quantity: Int, val name: String, val finalPrice: Double)