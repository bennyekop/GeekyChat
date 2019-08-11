package com.geekyindonesia.geekychat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.geekyindonesia.geekychat.model.MessageType
import com.geekyindonesia.geekychat.model.TextMessage
import com.geekyindonesia.geekychat.model.User
import com.geekyindonesia.geekychat.util.FirestoreUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ListenerRegistration
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_chat.*
import org.jetbrains.anko.toast
import java.util.*
import java.util.function.ToDoubleBiFunction

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

        FirestoreUtil.getCurrentUser {
            currentUser = it
        }

        val otherUserId = intent.getStringExtra(AppConstants.USER_ID)
        FirestoreUtil.getOrCreateChatChannel(otherUserId) { channelId ->
            currentChannelId = channelId

            messagesListenerRegistration =
                FirestoreUtil.addChatMessagesListener(channelId, this, this::updateRecyclerView)

            imageView_send.setOnClickListener {
                val messageToSend =
                    TextMessage(editText_message.text.toString(), Calendar.getInstance().time,
                        FirebaseAuth.getInstance().currentUser!!.uid,
                        otherUserId, currentUser.name)
                editText_message.setText("")
                FirestoreUtil.sendMessage(messageToSend, channelId)
            }

            fab_send_image.setOnClickListener(){
                TODO("buat kirim pesan gambar")
            }
        }
    }

    private fun updateRecyclerView(messages: List<com.xwray.groupie.kotlinandroidextensions.Item >) {
        fun init() {
            recycler_view_messages.apply {
                layoutManager = LinearLayoutManager(this@ChatActivity)
                adapter = GroupAdapter<ViewHolder>().apply {
                    messagesSection = Section(messages)
                    this.add(messagesSection)
                }
            }
            shouldInitRecyclerView = false
        }

        fun updateItems() = messagesSection.update(messages)

        if (shouldInitRecyclerView)
            init()
        else
            updateItems()

        recycler_view_messages.scrollToPosition(recycler_view_messages.adapter!!.itemCount -1)
    }
}
