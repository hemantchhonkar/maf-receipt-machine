package com.maf.receiptmachine.subscriber

import com.maf.receiptmachine.model.SelectedItemDetails

interface GenericSubscriber {
    fun processSelectedItem(selectedItemDetails: SelectedItemDetails)
    fun processAllTheItems()
}