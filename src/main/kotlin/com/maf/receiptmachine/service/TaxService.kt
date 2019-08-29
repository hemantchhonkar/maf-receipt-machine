package com.maf.receiptmachine.service

import com.maf.receiptmachine.model.Category
import com.maf.receiptmachine.model.Product

interface TaxService {
    fun getTaxAmount(product: Product): Double
    fun calculateTaxAmount(taxPercentage: Double, productPrice: Double): Double
    fun getFinalTaxForGivenCategory(category: Category, isImported: Boolean): Double
}