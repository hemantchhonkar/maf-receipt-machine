package com.maf.receiptmachine.service

import com.maf.receiptmachine.model.Category
import com.maf.receiptmachine.model.Product
import com.maf.receiptmachine.model.Store

class TaxService {

    val store = Store

    fun getTaxAmount(product: Product): Double {
        val taxPercentage = this.getTaxForGivenCategory(product.category)
        return calculateTaxAmount(taxPercentage, product.price)
    }

    fun calculateTaxAmount(taxPercentage: Double, productPrice: Double): Double {
        if (taxPercentage == 0.0) return 0.0
        return (taxPercentage * productPrice) / 100
    }

    fun getTaxForGivenCategory(category: Category): Double {
        return if (category === Category.IMPORTED) {
            getCategoryFromStore(category) + getCategoryFromStore(Category.OTHER)
        } else {
            getCategoryFromStore(category)
        }

    }

    private fun getCategoryFromStore(category: Category): Double {
        return store.categoryTaxMap.getOrElse(category, { 0.0 })
    }
}