package com.example.shirensupporter

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class Item(@PrimaryKey val name: String,
                val buyPrice: Int,
                val sellPrice: Int)

@Entity
open class FixedItem(name: String,buyPrice :Int,val fixedBuyPrice: Int,
                     sellPrice: Int,val fixedSellPrice :Int): Item(name,buyPrice,sellPrice)
