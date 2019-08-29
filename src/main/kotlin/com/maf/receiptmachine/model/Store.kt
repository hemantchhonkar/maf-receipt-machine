package com.maf.receiptmachine.model

object Store {
    var productList: List<Product> = listOf()
    var categoryTaxMap: Map<Category, Double> = mapOf()

    init {
        println("Store is being initialized")
        this.productList = initializeProductList()
        this.categoryTaxMap = initializeCategoryTaxMap()
    }

}

fun initializeCategoryTaxMap(): Map<Category, Double> {
    return mapOf(
        Category.OTHER to 10.0,
        Category.IMPORTED to 5.0
    )
}

fun initializeProductList(): List<Product> {
    val productList: ArrayList<Product> = arrayListOf()
    productList.add(Product(1, "Book", "", Category.BOOK, 12.49, false))
    productList.add(Product(2, "Music CD", "", Category.OTHER, 14.99, false))
    productList.add(Product(3, "Chocolate Bar", "", Category.FOOD, 0.85, false))
    productList.add(Product(4, "Imported box of chocolates", "", Category.FOOD, 10.00, true))
    productList.add(Product(5, "Imported bottle of perfume 1", "", Category.OTHER, 47.50, true))
    productList.add(Product(6, "Imported bottle of perfume 2", "", Category.OTHER, 27.99, true))
    productList.add(Product(7, "Bottle of perfume", "", Category.OTHER, 18.99, false))
    productList.add(Product(8, "Packet of headache pills", "", Category.MEDICINE, 9.75, false))
    productList.add(Product(9, "Box of imported chocolates ", "", Category.FOOD, 11.25, true))
    return productList
}

