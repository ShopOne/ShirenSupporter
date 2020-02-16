package com.example.shirensupporter

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.Fragment
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
        var idx = 0

        for(item in items[0]){
            val tableRow = inflater.inflate(R.layout.table_row,null)
            tableRow.nameRow.text = item.first
            tableRow.itemBuyPrice.text = item.second.toString()
            tableRow.itemSellPrice.text = item.third.toString()
            if(idx %2 == 0){
                val col = resources.getColor(R.color.gray)
                tableRow.nameRow.setBackgroundColor(col)
                tableRow.itemBuyPrice.setBackgroundColor(col)
                tableRow.itemSellPrice.setBackgroundColor(col)
            }
            idx++

            table.addView(tableRow)
        }

        return v;
    }
    val items = listOf(
        listOf(
            Triple("つるはし",240,100),
            Triple("こん棒",240,80),
            Triple("必中の剣",10000,5000),
            Triple("妖刀かまいたち",5000,2000),
            Triple("成仏の鎌",2000,900),
            Triple("長巻",500,200),
            Triple("ミノタウロスの斧",6000,2400)
        )
    )
}