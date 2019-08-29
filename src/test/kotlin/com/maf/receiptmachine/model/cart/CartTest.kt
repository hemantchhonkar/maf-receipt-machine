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
        Assert.assertTrue(cart.cartItemMap[1]?.quantity ?: 0 == 1)
        Assert.assertTrue(cart.cartItemMap[2]?.quantity ?: 0 == 1)
        Assert.assertTrue(3.25 == cart.totalTax)
        Assert.assertTrue(35.75 == cart.totalAmount)
    }

    @Test
    fun `should increase the quantity of an existing item if same product is being added again and should add taxes & total amount too`() {
        val cart = Cart()
        cart.addItem(CartItem(1, 1, "Music CD", 12.50), 1.25)
        cart.addItem(CartItem(2, 1, "Phone charger", 20.00), 2.00)
        cart.addItem(CartItem(1, 1, "Music CD", 12.50), 1.25)
        Assert.assertEquals(2, cart.cartItemMap.size)
        Assert.assertTrue(cart.cartItemMap[1]?.quantity ?: 0 == 2)
        Assert.assertTrue(cart.cartItemMap[2]?.quantity ?: 0 == 1)
        Assert.assertTrue(4.50 == cart.totalTax)
        Assert.assertTrue(49.50 == cart.totalAmount)
    }

    @Test
    fun `print cart details`() {
        val cart = Cart()
        cart.addItem(CartItem(1, 1, "Music CD", 12.50), 1.25)
        cart.addItem(CartItem(2, 1, "Phone charger", 20.00), 2.00)
        cart.addItem(CartItem(1, 1, "Music CD", 12.50), 1.25)
        cart.checkout()
    }

    @Test
    fun `find the cart item if it already exists in the cart`() {
        val cart = Cart()
        cart.addItem(CartItem(1, 1, "Music CD", 12.50), 1.25)
        val oldItem = cart.findIfProductAlreadyExist(1)
        Assert.assertNotNull(oldItem)
    }

    @Test
    fun `should add an item into cart`() {
        val cart = Cart()
        val item = CartItem(1, 1, "Music CD", 12.50)
        cart.processNewItem(item, 1.25)
        Assert.assertTrue(cart.cartItemMap.size == 1)
        val itemFromCart = cart.cartItemMap[1]
        Assert.assertTrue(itemFromCart != null && itemFromCart.finalPrice == 13.75)
    }

    @Test
    fun `update the details of the existing item if same item is being added again`() {
        val cart = Cart()
        val item = CartItem(1, 1, "Music CD", 12.50)
        cart.addItem(item, 1.25)
        cart.cartItemMap[1]?.let { cart.processExistingCartItem(oldItem = it, newItem = item, taxOnItem = 1.25) }
        Assert.assertTrue(cart.cartItemMap.size == 1)
        val itemFromCart = cart.cartItemMap[1]
        Assert.assertTrue(itemFromCart != null && itemFromCart.finalPrice == 27.5)
    }

    @Test
    fun `should print cart details on checkout and clear the cart`() {
        val cart = Cart()
        val item = CartItem(1, 1, "Music CD", 12.50)
        cart.addItem(item, 1.25)
        cart.addItem(item, 1.25)

        Assert.assertTrue(cart.cartItemMap.size == 1)
        val itemFromCart = cart.cartItemMap[1]
        Assert.assertTrue(itemFromCart != null && itemFromCart.finalPrice == 27.5)
        Assert.assertTrue(cart.totalTax == 2.50)
        Assert.assertTrue(cart.totalAmount == 27.5)
        cart.checkout()

        Assert.assertTrue(cart.cartItemMap.size == 0)
        Assert.assertTrue(cart.totalTax == 0.0)
        Assert.assertTrue(cart.totalAmount == 0.0)
    }
}