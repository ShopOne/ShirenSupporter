package com.example.shirensupporter

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fixed_item_row.view.*
import kotlinx.android.synthetic.main.fixed_item_table.view.*
import kotlinx.android.synthetic.main.item_table.view.*
import kotlinx.android.synthetic.main.item_row.view.*

const val ITEM_PREF = "ITEM_PREF"

class ItemsFragment(private val cnt: Int,private val prefs: SharedPreferences): Fragment(){
    private var checkCol :Int = 0
    private var whiteCol :Int = 0
    private var grayCol: Int = 0
    lateinit var nowView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checkCol = ContextCompat.getColor(context!!,R.color.checked_col)
        whiteCol = ContextCompat.getColor(context!!,R.color.white)
        grayCol = ContextCompat.getColor(context!!,R.color.gray)
        if(items[cnt][0] is FixedItem){
            val v =  inflater.inflate(R.layout.fixed_item_table,container,false)
            val table = v.fTableLayout
            items[cnt].forEach {item->
                if(item is FixedItem){
                    val fTableRow = View.inflate(context,R.layout.fixed_item_row,null)
                    fTableRow.fNameRow.text = item.name
                    fTableRow.fItemBuyPrice.text = item.buyPrice.toString()
                    fTableRow.fItemFixedBuyPrice.text = item.fixedBuyPrice.toString()
                    fTableRow.fItemSellPrice.text = item.sellPrice.toString()
                    fTableRow.fItemFixedSellPrice.text = item.fixedSellPrice.toString()
                    fTableRow.setOnClickListener(RowClickedListener())
                    fTableRow.fNameRow.setOnClickListener(RowClickedListener())
                    fTableRow.fNameRow.setBackgroundColor(
                        if(prefs.getBoolean(item.name,true)) whiteCol
                        else checkCol
                    )

                    table.addView(fTableRow)
                }
            }
            nowView = v
            return v
        }else{
            val v =  inflater.inflate(R.layout.item_table,container,false)
            val table = v.tableLayout
            items[cnt].forEach {item->
                val tableRow = View.inflate(context,R.layout.item_row,null)
                tableRow.nameRow.text = item.name
                tableRow.itemBuyPrice.text = item.buyPrice.toString()
                tableRow.itemSellPrice.text = item.sellPrice.toString()
                tableRow.setOnClickListener(RowClickedListener())

                tableRow.nameRow.setBackgroundColor(
                    if(prefs.getBoolean(item.name,true)) whiteCol
                    else checkCol
                )
                tableRow.nameRow.setOnClickListener(RowClickedListener())

                table.addView(tableRow)
            }
            nowView = v
            return v
        }
    }
    private inner class RowClickedListener: View.OnClickListener{
        override fun onClick(p0: View?) {
            if(p0 is TextView){
                val nb = !prefs.getBoolean(p0.text.toString(),true)
                p0.setBackgroundColor(
                    if(nb) whiteCol
                    else checkCol
                )
                prefs.edit()
                    .putBoolean(p0.text.toString(),nb)
                    .apply()
            }
        }
    }
    val items = listOf(
        listOf(
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
        ),
        listOf(
            FixedItem("皮甲の盾",1000,40,350,20),
            FixedItem("木甲の盾",600,40,200,15),
            FixedItem("トドの盾",2000,100,800,40),
            FixedItem("地雷ナバリの盾",4000,200,1500,75),
            FixedItem("見かけだおしの盾",10000,1000,2500,250),
            FixedItem("青銅甲の盾",300,30,100,10),
            FixedItem("バトルカウンター",5000,250,2500,125),
            FixedItem("見切りの盾",7500,500,3000,150),
            FixedItem("やまびこの盾",12000,600,6000,300),
            FixedItem("鉄甲の盾",1800,180,750,75),
            FixedItem("ドラゴンシールド",5000,250,2500,125),
            FixedItem("重装の盾",3000,300,1000,100),
            FixedItem("風魔の盾",5000,500,2500,250),
            FixedItem("使い捨ての盾",2000,200,750,75),
            FixedItem("ラセン風魔の盾",50000,5000,22500,2250)
        ),
        listOf(
            FixedItem("ガイコツまどうの杖",300,30,150,15),
            FixedItem("しあわせの杖",500,50,250,25),
            FixedItem("不幸の杖",500,50,250,25),
            FixedItem("ふきとばしの杖",500,50,250,25),
            FixedItem("一時しのぎの杖",500,50,250,25),
            FixedItem("場所替えの杖",700,70,350,35),
            FixedItem("かなしばりの杖",1000,100,500,50),
            FixedItem("身がわりの杖",1000,100,500,50),
            FixedItem("痛み分けの杖",1000,100,500,50),
            FixedItem("封印の杖",1000,100,500,50),
            FixedItem("ブフーの杖",2000,200,500,50)
        ),
        listOf(
            FixedItem("弱化の壺",1000,100,200,20),
            FixedItem("識別の壺",1000,100,500,50),
            FixedItem("変化の壺",1000,100,500,50),
            FixedItem("倉庫の壺",2500,250,1200,120),
            FixedItem("分裂の壺",3000,300,1500,150),
            FixedItem("合成の壺",3500,350,1500,150),
            FixedItem("うっぷんばらしの壺",7500,750,1500,150),
            FixedItem("アホくさい壺",7500,750,1500,150),
            FixedItem("割れない壺",10000,1000,1500,150),
            FixedItem("強化の壺",10000,1000,5000,500),
            FixedItem("ガイバラの壺",15000,1500,1500,150),
            FixedItem("背中の壺",1500,150,700,70),
            FixedItem("トドの壺",1600,160,600,60),
            FixedItem("魔物のるつぼ",2000,200,300,30),
            FixedItem("やりすごしの壺",1000,0,400,0),
            FixedItem("保存の壺",1600,0,600,0),
            FixedItem("底抜けの壺",2500,0,400,0)
        ),
        listOf(
            Item("遠投の腕輪",1200,600),
            Item("呪いよけの腕輪",2400,1200),
            Item("混乱よけの腕輪",2400,1200),
            Item("錆よけの腕輪",2400,1200),
            Item("通過の腕輪",2400,1200),
            Item("レベル固定の腕輪",2400,1200),
            Item("垂れ流しの腕輪",2400,1200),
            Item("透視の腕輪",3600,1800),
            Item("回復の腕輪",5000,2500),
            Item("ワナ師の腕輪",5000,2500),
            Item("しあわせの腕輪",10000,5000),
            Item("会心の腕輪",10000,5000),
            Item("痛恨の腕輪",10000,5000),
            Item("識別の腕輪",30000,15000)
        ),
        listOf(
            Item("モンスターハウスの巻物",80,40),
            Item("混乱の巻物",100,50),
            Item("敵倍速の巻物",100,50),
            Item("バクスイの巻物",200,100),
            Item("迷子の巻物",200,100),
            Item("識別の巻物",300,150),
            Item("あかりの巻物",300,250),
            Item("拾えずの巻物",300,300),
            Item("自爆の巻物",500,100),
            Item("おはらいの巻物",500,250),
            Item("くちなしの巻物",500,250),
            Item("地の恵みの巻物",800,400),
            Item("天の恵みの巻物",800,400),
            Item("パワーアップの巻物",1000,100),
            Item("困ったときの巻物",1000,250),
            Item("壺増大の巻物",1000,500),
            Item("真空切りの巻物",1000,500),
            Item("ワナの巻物",1000,500),
            Item("メッキの巻物",1500,500),
            Item("吸い出しの巻物",1500,750),
            Item("白紙の巻物",3000,1000),
            Item("ジェノサイドの巻物",50000,750)
        ),
        listOf(
            Item("雑草",50,25),
            Item("めぐすり草",50,25),
            Item("薬草",50,25),
            Item("どく草",50,25),
            Item("弟切草",100,50),
            Item("超不幸の種",200,30),
            Item("不幸の種",200,40),
            Item("くねくね草",200,50),
            Item("胃縮小の種",200,60),
            Item("キグニ族の種",200,100),
            Item("命の草",500,200),
            Item("ドラゴン草",500,200),
            Item("ちからの草",500,250),
            Item("どく消し草",650,250),
            Item("胃拡張の種",1000,500),
            Item("しあわせ草",1000,500),
            Item("無敵草",3000,100),
            Item("混乱草",3000,1500),
            Item("睡眠草",5000,2500),
            Item("復活の草",5000,2500),
            Item("天使の草",10000,1500)
        )
    )
}