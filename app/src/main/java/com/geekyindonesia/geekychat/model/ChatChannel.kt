package com.geekyindonesia.geekychat.model

data class ChatChannel(val userIds: MutableList<String>) {
    constructor() : this(mutableListOf())
}