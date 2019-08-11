package com.geekyindonesia.geekychat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.geekyindonesia.geekychat.model.TextMessage
import com.geekyindonesia.geekychat.model.User
import com.geekyindonesia.geekychat.util.FirestoreUtil
import com.google.firebase.firestore.ListenerRegistration
import com.xwray.groupie.Item
import com.xwray.groupie.Section
import org.jetbrains.anko.toast

class ChatActivity : AppCompatActivity() {

    private lateinit var currentChannelId: String
    private lateinit var currentUser: User
    private lateinit var otherUserId: String

    private lateinit var messagesListenerRegistration: ListenerRegistration
    private var shouldInitRecyclerView = true
    private lateinit var messagesSection: Section

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra(AppConstants.USER_NAME)

        val otherUserId = intent.getStringExtra(AppConstants.USER_ID)
        FirestoreUtil.getOrCreateChatChannel(otherUserId) { channelId ->
            currentChannelId = channelId

            messagesListenerRegistration =
                FirestoreUtil.addChatMessagesListener(channelId, this, this::onMessageChange)
        }
    }

    private fun onMessageChange(messages: List<com.xwray.groupie.kotlinandroidextensions.Item>) {
        toast("onMessageChange Berjalan !")
    }
}
