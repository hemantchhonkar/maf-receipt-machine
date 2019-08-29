package com.maf.receiptmachine.model.cart

import java.util.*

class Cart {
    val cartItemMap: SortedMap<Long, CartItem> = sortedMapOf()
    var totalTax: Double = 0.0
    var totalAmount: Double = 0.0

    fun addItem(item: CartItem, taxOnItem: Double) {
        val oldItem = this.cartItemMap[item.productId]
        if (oldItem != null) {
            this.cartItemMap[item.productId] = oldItem.copy(
                quantity = oldItem.quantity + item.quantity,
                finalPrice = oldItem.finalPrice + item.finalPrice
            )
        } else {
            this.cartItemMap[item.productId] = item
        }
        this.totalTax += taxOnItem
        this.totalAmount += item.finalPrice
    }

    fun checkout() {
        this.cartItemMap.forEach { _, cartItem ->
            println("${cartItem.quantity} ${cartItem.name} ${cartItem.finalPrice}")
        }
        println("Sales Taxes: $totalTax")
        println("Total: $totalAmount")

    }

    fun cleanTheCart() {
        this.cartItemMap.clear()
        this.totalTax = 0.0
        this.totalAmount = 0.0
    }
}