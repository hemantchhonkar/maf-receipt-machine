package com.maf.receiptmachine.model.cart

import org.junit.Assert
import org.junit.Test


class CartTest {

    @Test
    fun `should add items into the cart and set tax and total amount`() {
        val cart = Cart()
        cart.addItem(CartItem(1, 1, "Music CD", 12.50), 1.25)
        cart.addItem(CartItem(2, 1, "Phone charger", 20.00), 2.00)
        Assert.assertEquals(2, cart.cartItemMap.size)
        Assert.assertTrue(cart.cartItemMap[1]?.quantity ?:0  == 1)
        Assert.assertTrue(cart.cartItemMap[2]?.quantity ?:0  == 1)
        Assert.assertTrue(3.25 == cart.totalTax)
        Assert.assertTrue(32.50 == cart.totalAmount)
    }

    @Test
    fun `should increase the quantity of an existing item if same product is being added again and should add taxes & total amount too`() {
        val cart = Cart()
        cart.addItem(CartItem(1, 1, "Music CD", 12.50), 1.25)
        cart.addItem(CartItem(2, 1, "Phone charger", 20.00), 2.00)
        cart.addItem(CartItem(1, 1, "Music CD", 12.50), 1.25)
        Assert.assertEquals(2, cart.cartItemMap.size)
        Assert.assertTrue(cart.cartItemMap[1]?.quantity ?:0  == 2)
        Assert.assertTrue(cart.cartItemMap[2]?.quantity ?:0  == 1)
        Assert.assertTrue(4.50 == cart.totalTax)
        Assert.assertTrue(45.00 == cart.totalAmount)
    }

    @Test
    fun `print cart details`() {
        val cart = Cart()
        cart.addItem(CartItem(1, 1, "Music CD", 12.50), 1.25)
        cart.addItem(CartItem(2, 1, "Phone charger", 20.00), 2.00)
        cart.addItem(CartItem(1, 1, "Music CD", 12.50), 1.25)
        cart.checkout();
    }
}