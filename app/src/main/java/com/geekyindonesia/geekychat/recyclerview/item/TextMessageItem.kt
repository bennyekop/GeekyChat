package com.geekyindonesia.geekychat.recyclerview.item


import android.content.Context
import com.geekyindonesia.geekychat.R
import com.geekyindonesia.geekychat.model.TextMessage
import com.xwray.groupie.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
//import kotlinx.android.synthetic.main.item_text_message.*


class TextMessageItem(val message: TextMessage,
                      val context: Context)
    : com.xwray.groupie.kotlinandroidextensions.Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        //TODO: place holder bind
    }

    override fun getLayout() = R.layout.item_text_message

}