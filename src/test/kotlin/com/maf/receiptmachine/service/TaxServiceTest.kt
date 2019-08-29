package com.maf.receiptmachine.service

import com.maf.receiptmachine.model.Category
import com.maf.receiptmachine.model.Product
import org.junit.Assert
import org.junit.Test

class TaxServiceTest {
    private val taxServiceImpl = TaxServiceImpl()

    @Test
    fun `Should calculate and return final Tax Amount for given product - Non Imported and Non- Exempted category`() {
        val product = Product(2, "Music CD", "", Category.OTHER, 14.99, false)
        val finalTaxAmount = taxServiceImpl.getTaxAmount(product)
        Assert.assertTrue(finalTaxAmount == 1.499)
    }

    @Test
    fun `Should calculate and return final Tax Amount for given product - Non Imported and Exempted category`() {
        val product = Product(3, "Chocolate Bar", "", Category.FOOD, 0.85, false)
        val finalTaxAmount = taxServiceImpl.getTaxAmount(product)
        Assert.assertTrue(finalTaxAmount == 0.00)
    }

    @Test
    fun `Should calculate and return final Tax Amount for given product - Imported and Non- Exempted category`() {
        val product = Product(2, "Music CD", "", Category.OTHER, 14.99, true)
        val finalTaxAmount = taxServiceImpl.getTaxAmount(product)
        Assert.assertTrue(finalTaxAmount == 2.2485)
    }

    @Test
    fun `Should calculate and return final Tax Amount for given product - Imported and Exempted category`() {
        val product = Product(3, "Chocolate Bar", "", Category.FOOD, 0.85, true)
        val finalTaxAmount = taxServiceImpl.getTaxAmount(product)
        Assert.assertTrue(finalTaxAmount == 0.0425)
    }

    @Test
    fun `Should return NON-Zero value if category exists`() {
        var taxPercentage = taxServiceImpl.getCategoryTaxFromStore(Category.OTHER)
        Assert.assertTrue(taxPercentage == 10.00)
        taxPercentage = taxServiceImpl.getCategoryTaxFromStore(Category.IMPORTED)
        Assert.assertTrue(taxPercentage == 5.00)
    }

    @Test
    fun `Should return Zero if category exists`() {
        var taxPercentage = taxServiceImpl.getCategoryTaxFromStore(Category.FOOD)
        Assert.assertTrue(taxPercentage == 0.00)
        taxPercentage = taxServiceImpl.getCategoryTaxFromStore(Category.MEDICINE)
        Assert.assertTrue(taxPercentage == 0.00)
    }

    @Test
    fun `Should return Zero or the particular tax amount if isImported = false`() {
        var taxPercentage =
            taxServiceImpl.getFinalTaxForGivenCategory(Category.FOOD, false)
        Assert.assertTrue(taxPercentage == 0.00)
        taxPercentage =
            taxServiceImpl.getFinalTaxForGivenCategory(Category.OTHER, false)
        Assert.assertTrue(taxPercentage == 10.00)
    }

    @Test
    fun `Should return  tax amount + 5 if isImported = true`() {
        var taxPercentage =
            taxServiceImpl.getFinalTaxForGivenCategory(Category.FOOD, true)
        Assert.assertTrue(taxPercentage == 5.00)
        taxPercentage =
            taxServiceImpl.getFinalTaxForGivenCategory(Category.OTHER, true)
        Assert.assertTrue(taxPercentage == 15.00)
    }
}