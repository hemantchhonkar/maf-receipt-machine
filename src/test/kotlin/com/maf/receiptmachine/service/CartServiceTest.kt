package com.maf.receiptmachine.service

import com.maf.receiptmachine.model.Category
import org.junit.Assert
import org.junit.Test

class CartServiceTest {
    val cartService = CartService()

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

    @Test
    fun `should return a TAX value if entry exists for that category`() {
        var tax: Double = cartService.getTaxForGivenCategory(Category.IMPORTED)
        Assert.assertNotEquals(0.0, tax)
        tax = cartService.getTaxForGivenCategory(Category.OTHER)
        Assert.assertNotEquals(0.0, tax)
    }

    @Test
    fun `should return "0_0" as a TAX value if entry doesn't exist for that category`() {
        var tax: Double = cartService.getTaxForGivenCategory(Category.BOOK)
        Assert.assertTrue(0.0 ==  tax)
        tax = cartService.getTaxForGivenCategory(Category.FOOD)
        Assert.assertTrue(0.0 ==  tax)
    }
}