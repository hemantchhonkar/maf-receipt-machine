package com.maf.receiptmachine.util

fun formatDouble(value: Double): String {
    return  String.format("%.2f", value)
}

fun roundDouble(value: Double): Double {
   return  Math.round(value * 20) / 20.0
}