package com.maf.receiptmachine.listener

import com.maf.receiptmachine.model.SelectedItemDetails
import com.maf.receiptmachine.subscriber.GenericSubscriber

class ProductInputListener {
    val subscriberList: ArrayList<GenericSubscriber> = arrayListOf()

    fun subscribe(subscriber: GenericSubscriber) {
        subscriberList.add(subscriber)
    }

    fun notifySubscriberWithItem(selectedItemDetails: SelectedItemDetails) {
        subscriberList.forEach{subscriber ->
            subscriber.processSelectedItem(selectedItemDetails)
        }
    }

    fun notifySubscriberToProcessAllTheItems(){
        subscriberList.forEach{subscriber ->
            subscriber.processAllTheItems()
        }
    }
}