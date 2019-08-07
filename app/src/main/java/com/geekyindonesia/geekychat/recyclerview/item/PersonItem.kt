package com.geekyindonesia.geekychat.recyclerview.item

import android.content.Context
import com.geekyindonesia.geekychat.R
import com.geekyindonesia.geekychat.glide.GlideApp
import com.geekyindonesia.geekychat.model.User
import com.geekyindonesia.geekychat.util.StorageUtil
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_person.*

class PersonItem(val person: User,
                 val userId: String,
                 private val context: Context
)
    : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView_name.text = person.name
        viewHolder.textView_bio.text = person.bio
        if (person.profilPicturePath != null)
            GlideApp.with(context)
                .load(StorageUtil.pathToReference(person.profilPicturePath))
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(viewHolder.imageView_profile_picture)
    }

    override fun getLayout() = R.layout.item_person
}

