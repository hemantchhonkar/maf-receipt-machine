package com.maf.receiptmachine.subscriber

import com.maf.receiptmachine.model.SelectedItemDetails
import org.junit.Assert
import org.junit.Test

class CartSubscriberTest {
    val cartSubscriber = CartSubscriber()

    @Test
    fun `CartService Should be initialized `() {
        Assert.assertNotNull(cartSubscriber.cartService)
    }

    @Test
    fun `Should process selectedItem details successfully and add an item into the cart`() {
        cartSubscriber.processSelectedItem(SelectedItemDetails(1,1))
    }

    @Test(expected = NoSuchElementException::class)
    fun `Should process selectedItem details with an exception`() {
        cartSubscriber.processSelectedItem(SelectedItemDetails(20,1))
    }

    @Test
    fun `Should process all item successfully`() {
        cartSubscriber.processAllTheItems()
    }
}