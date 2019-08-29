package com.maf.receiptmachine.service

import com.maf.receiptmachine.model.Category
import com.maf.receiptmachine.model.Product
import com.maf.receiptmachine.model.SelectedItemDetails
import org.junit.Assert
import org.junit.Test

class CartServiceTest {
    private val cartService = CartServiceImpl()

    @Test
    fun `should find and return a product, if exist in the productList`() {
        val product = cartService.getProductDetails(1)
        Assert.assertNotNull(product)
    }

    @Test
    fun `should return NULL, if product doesn't exist in the productList`() {
        val product = cartService.getProductDetails(20)
        Assert.assertNull(product)
    }

    @Test(expected = NoSuchElementException::class)
    fun `Should throw exception if product does not exist in the Store`() {
        cartService.processAndAddItemToTheCart(SelectedItemDetails(15, 10))
    }

    @Test
    fun `Should add item successfully in the cart`() {
        cartService.processAndAddItemToTheCart(SelectedItemDetails(1, 2))
        val itemFromCart = cartService.cart.cartItemMap[1]
        Assert.assertNotNull(itemFromCart)
        if (itemFromCart != null) {
            Assert.assertTrue(itemFromCart.finalPrice == 24.98)
            Assert.assertTrue(itemFromCart.quantity == 2)
            Assert.assertTrue(itemFromCart.productId == 1L)
        }
    }

    @Test
    fun `Should multiply the price with quantity`() {
        val finalPrice = cartService.calculateFinalPrice(12.25, 2)
        Assert.assertTrue(finalPrice == 24.50)
    }

    @Test
    fun `Should prepare CartItem from SelectedDetails and Product from DB`() {
        val productFromDB = Product(1, "Book", "", Category.BOOK, 12.49, false)
        val selectedItemDetails = SelectedItemDetails(1, 2)
        val cartItem = cartService.prepareCartItem(productFromDB, selectedItemDetails)
        Assert.assertNotNull(cartItem)
        Assert.assertTrue(cartItem.finalPrice == 24.98)
        Assert.assertTrue(cartItem.quantity == 2)
        Assert.assertTrue(cartItem.productId == productFromDB.id)
    }

    @Test
    fun `should checkout cart items and clear the cart`() {
        cartService.processAndAddItemToTheCart(SelectedItemDetails(2, 2))
        Assert.assertTrue(cartService.cart.cartItemMap.size > 0)
        Assert.assertTrue(cartService.cart.totalTax > 0)
        Assert.assertTrue(cartService.cart.totalAmount > 0)

        cartService.checkOutCartItems()
        Assert.assertTrue(cartService.cart.cartItemMap.size == 0)
        Assert.assertTrue(cartService.cart.totalTax == 0.0)
        Assert.assertTrue(cartService.cart.totalAmount == 0.0)
    }

}