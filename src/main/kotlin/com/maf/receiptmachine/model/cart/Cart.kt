package com.maf.receiptmachine.model.cart

import com.maf.receiptmachine.util.formatDouble
import java.util.*

class Cart {
    val cartItemMap: SortedMap<Long, CartItem> = sortedMapOf()
    var totalTax: Double = 0.0
    var totalAmount: Double = 0.0

    fun addItem(itemToAdd: CartItem, taxOnItem: Double) {
        val oldItem = this.findIfProductAlreadyExist(itemToAdd.productId)
        if (oldItem != null) {
            this.processExistingCartItem(oldItem, itemToAdd, taxOnItem)

        } else {
            this.processNewItem(itemToAdd, taxOnItem)
            setTotalAmount(itemToAdd, taxOnItem)
        }
        this.totalTax += taxOnItem

    }

    private fun setTotalAmount(itemToAdd: CartItem, taxOnItem: Double) {
        this.totalAmount += (itemToAdd.finalPrice + taxOnItem)
    }


    fun findIfProductAlreadyExist(cartItemProductId: Long): CartItem? {
        return this.cartItemMap[cartItemProductId]
    }

    fun checkout() {
        println("\n\n********************* Items in the cart *******************\n")
        this.cartItemMap.forEach { _, cartItem ->
            println("${cartItem.quantity} ${cartItem.name} ${formatDouble(cartItem.finalPrice)}")
        }
        println("\n------------------- Cost details ----------------------\n")
        println("Sales Taxes: ${formatDouble(totalTax)}")
        println("Total: ${formatDouble(totalAmount)} \n")
        println("-------------------------- End ---------------------------\n")
        cleanTheCart()

    }

    fun processExistingCartItem(oldItem: CartItem, newItem: CartItem, taxOnItem: Double) {
        val updatedItem = oldItem.copy(
            quantity = (oldItem.quantity + newItem.quantity),
            finalPrice = (oldItem.finalPrice + (newItem.finalPrice + taxOnItem))
        )
        setTotalAmount(newItem, taxOnItem)
        this.cartItemMap[newItem.productId] = updatedItem
    }

    fun processNewItem(itemToAdd: CartItem, taxOnItem: Double) {
        this.cartItemMap[itemToAdd.productId] =
            itemToAdd.copy(finalPrice = itemToAdd.finalPrice + taxOnItem)
    }

    fun cleanTheCart() {
        this.cartItemMap.clear()
        this.totalTax = 0.0
        this.totalAmount = 0.0
    }
}