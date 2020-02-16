package com.example.shirensupporter

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fixed_item_table.view.*
import kotlinx.android.synthetic.main.item_table.*
import kotlinx.android.synthetic.main.item_table.view.*
import kotlinx.android.synthetic.main.table_row.view.*
import org.w3c.dom.ls.LSResourceResolver


class ItemsFragment(val cnt: Int): Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.item_table,container,false)
        val table = v.tableLayout

        Log.d("hogefugaa",items.toString())
        items[0].forEachIndexed {idx,item->
            val tableRow = inflater.inflate(R.layout.table_row,null)
            tableRow.nameRow.text = item.name
            tableRow.itemBuyPrice.text = item.buyPrice.toString()
            tableRow.itemSellPrice.text = item.sellPrice.toString()
            if(idx %2 == 0){
                val col = resources.getColor(R.color.gray)
                tableRow.nameRow.setBackgroundColor(col)
                tableRow.itemBuyPrice.setBackgroundColor(col)
                tableRow.itemSellPrice.setBackgroundColor(col)

            }


            table.addView(tableRow)
        }


        return v;
    }
    val items = listOf(
        listOf<Item>(
            FixedItem("つるはし",240,12,100,7),
            FixedItem("こん棒",240,24,80,8),
            FixedItem("必中の剣",10000,900,5000,8),
            FixedItem("妖刀かまいたち",5000,380,2000,170),
            FixedItem("成仏の鎌",2000,150,900,75),
            FixedItem("長巻",500,50,200,20),
            FixedItem("ミノタウロスの斧",6000,450,2400,200),
            FixedItem("ドレインバスター",4000,320,1800,200),
            FixedItem("１ツ目殺し",3600,260,1500,160),
            FixedItem("ブフーの包丁",2000,100,800,55),
            FixedItem("カタナ",800,80,300,30),
            FixedItem("ドラゴンキラー",3600,310,1200,107),
            FixedItem("剛剣マンジカブラ",15000,1500,7000,700),
            FixedItem("火迅風魔刀",30000,3000,12500,1250),
            FixedItem("秘剣カブラステギ",40000,4000,17500,1750)

        )
    )
}