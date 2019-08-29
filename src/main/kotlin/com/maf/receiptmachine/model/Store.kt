package com.maf.receiptmachine.model

object Store {
    var productList: List<Product> = listOf()
    var categoryTaxMap: Map<Category, Double> = mapOf()

    init {
        print("Store is being initialized")
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
    val productList: ArrayList<Product> = arrayListOf<Product>()
    productList.add(Product(1, "Book", "", Category.BOOK, 12.49))
    productList.add(Product(2, "Music CD", "", Category.OTHER, 14.99))
    productList.add(Product(3, "Chocolate Bar", "", Category.FOOD, 0.85))
    productList.add(Product(4, "Imported box of chocolates", "", Category.IMPORTED, 10.00))
    productList.add(Product(5, "Imported bottle of perfume", "", Category.IMPORTED, 47.50))
    productList.add(Product(6, "Imported bottle of perfume", "", Category.IMPORTED, 27.99))
    productList.add(Product(7, "Bottle of perfume", "", Category.OTHER, 18.99))
    productList.add(Product(8, "Packet of headache pills", "", Category.MEDICINE, 9.75))
    return productList
}