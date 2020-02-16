package com.example.shirensupporter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.item_table.*
import kotlinx.android.synthetic.main.item_table.view.*
import kotlinx.android.synthetic.main.table_row.view.*


class ItemsFragment(val cnt: Int): Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.item_table,container,false)
        val table = v.tableLayout
        var tableRow = inflater.inflate(R.layout.table_row,null)

        tableRow.nameRow.text = "ジェノサイド"
        tableRow.itemBuyPrice.text = "ほげ"
        tableRow.itemSellPrice.text = "ふが"
        tableRow.itemFixedVal.text = "に"

        table.addView(tableRow)
        return v;
    }
}