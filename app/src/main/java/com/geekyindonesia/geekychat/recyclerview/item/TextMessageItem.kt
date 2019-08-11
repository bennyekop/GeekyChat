package com.geekyindonesia.geekychat.recyclerview.item


import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import com.geekyindonesia.geekychat.R
import com.geekyindonesia.geekychat.model.TextMessage
import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_text_message.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.wrapContent
import java.text.SimpleDateFormat
import java.util.logging.SimpleFormatter

//import kotlinx.android.synthetic.main.item_text_message.*


class TextMessageItem(val message: TextMessage,
                      val context: Context)
    : com.xwray.groupie.kotlinandroidextensions.Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView_message_text.text = message.text
        setTimeText(viewHolder)
        setMessageRootGravity(viewHolder)
        //super.bind (viewHolder, position)
    }

    private fun setTimeText(viewHolder: ViewHolder) {
        val dateFormat = SimpleDateFormat
            .getDateInstance(SimpleDateFormat.SHORT)
        viewHolder.textView_message_time.text = dateFormat.format(message.time)
    }

    private fun setMessageRootGravity(viewHolder: ViewHolder) {
        if (message.senderId == FirebaseAuth.getInstance().currentUser?.uid) {
            viewHolder.message_root.apply {
                this
                backgroundResource = R.drawable.rect_round_white
                val lParams = FrameLayout.LayoutParams(wrapContent, wrapContent, Gravity.END)
                this.layoutParams = lParams
            }
        } else {
            viewHolder.message_root.apply {
                this
                backgroundResource = R.drawable.rect_round_primary_color
                val lParams = FrameLayout.LayoutParams(wrapContent, wrapContent, Gravity.START)
                this.layoutParams = lParams
            }

        }
    }

    override fun getLayout() = R.layout.item_text_message

    override fun isSameAs(other: Item<*>?): Boolean {
        if (other !is TextMessageItem)
            return false
        if (this.message != other.message)
            return false
        return true

    }

    override fun equals(other: Any?): Boolean {
        return isSameAs(other as? TextMessageItem)
    }

}
