package com.example.shirensupporter

open class Item(val name: String,
                val buyPrice: Int,
                val sellPrice: Int)

open class FixedItem(name: String,buyPrice :Int,sellPrice: Int,
                     val fixedBuyPrice: Int,val fixedSellPrice :Int): Item(name,buyPrice,sellPrice)
