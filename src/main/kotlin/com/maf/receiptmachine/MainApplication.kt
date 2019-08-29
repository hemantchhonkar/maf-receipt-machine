@file:JvmName("MainApplication")

package com.maf.receiptmachine

import com.maf.receiptmachine.listener.ProductInputListener
import com.maf.receiptmachine.model.SelectedItemDetails
import com.maf.receiptmachine.model.Store
import com.maf.receiptmachine.subscriber.CartSubscriber
import java.util.*
import kotlin.system.exitProcess

val productInputListener = ProductInputListener()
val cartSubscriber = CartSubscriber()

fun main() {
    productInputListener.subscribe(cartSubscriber)
    printProductsFromStore()
    while (true) {
        val scanner = Scanner(System.`in`)
        print("Enter desired product id: ")
        val productId = scanner.nextInt()
        print("Enter number of products: ")
        val units = scanner.nextInt()
        processInput(productId, units)
        print("Wanna add more items, if yes then enter Y otherwise enter N to checkout")
        val command = readLine()
        if (command != null && "N" == command.toUpperCase()) {
            proceedToCheckout()
            exitProcess(0)
        }
    }
}

fun proceedToCheckout() {
    productInputListener.notifySubscriberToProcessAllTheItems()
}

fun processInput(productId: Int, units: Int) {
    val selectedItemDetails = SelectedItemDetails(productId.toLong(), units)
    productInputListener.notifySubscriberWithItem(selectedItemDetails)
}

fun printProductsFromStore() {
    val store = Store
    println("**************  Product List  **************")
    store.productList.forEach { product ->
        println("${product.id}  ${product.name}  ${product.price}")
    }
    println("*********************************************")
    println(
        "Please enter Product ID from above list to add items into the cart." +
                " Enter DONE when you want to checkout."
    )
}