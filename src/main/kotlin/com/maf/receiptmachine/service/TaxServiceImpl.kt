package com.maf.receiptmachine.service

import com.maf.receiptmachine.model.Category
import com.maf.receiptmachine.model.Product
import com.maf.receiptmachine.model.Store

class TaxServiceImpl: TaxService {

    private val store = Store

    override fun getTaxAmount(product: Product): Double {
        val taxPercentage = this.getFinalTaxForGivenCategory(product.category, product.isImported)
        return calculateTaxAmount(taxPercentage, product.price)
    }

    override fun calculateTaxAmount(taxPercentage: Double, productPrice: Double): Double {
        if (taxPercentage == 0.0) return 0.0
        return (taxPercentage * productPrice) / 100
    }

    override fun getFinalTaxForGivenCategory(category: Category, isImported: Boolean): Double {
        return if (isImported) {
            getCategoryTaxFromStore(category) + getCategoryTaxFromStore(Category.IMPORTED)
        } else {
            getCategoryTaxFromStore(category)
        }

    }

    fun getCategoryTaxFromStore(category: Category): Double {
        return store.categoryTaxMap.getOrElse(category, { 0.0 })
    }
}