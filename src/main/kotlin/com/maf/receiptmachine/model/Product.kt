package com.maf.receiptmachine.model

data class Product(val id: Long, val name: String,
                   val description: String, val category: Category,
                   val price: Double)