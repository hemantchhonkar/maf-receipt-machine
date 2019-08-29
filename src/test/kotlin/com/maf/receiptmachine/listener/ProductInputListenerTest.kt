package com.maf.receiptmachine.listener

import com.maf.receiptmachine.model.SelectedItemDetails
import com.maf.receiptmachine.subscriber.CartSubscriber
import org.junit.Assert
import org.junit.Test

class ProductInputListenerTest {
    private val productInputListener = ProductInputListener()

    @Test
    fun `Should add the subscriber into the subscriber list`() {
        Assert.assertTrue(productInputListener.subscriberList.size == 0)
        productInputListener.subscribe(CartSubscriber())
        Assert.assertTrue(productInputListener.subscriberList.size == 1)
    }

    @Test
    fun `Should notify all the subscribers about the SelectItem`() {
        productInputListener.notifySubscriberWithItem(SelectedItemDetails(1, 1))
    }

    @Test
    fun `Should notify all the subscribers to processAll the items`() {
        productInputListener.notifySubscriberToProcessAllTheItems()
    }
}