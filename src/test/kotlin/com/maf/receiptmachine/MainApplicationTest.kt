package com.maf.receiptmachine

import org.junit.Test

class MainApplicationTest {

    @Test
    fun `Process inputs successfully`() {
        processInput(1, 2)
    }

    @Test
    fun `Should print all the item from store`() {
        printProductsFromStore()
    }

}