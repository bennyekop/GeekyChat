package com.geekyindonesia.geekychat.model

data class User(val name: String,
                val bio: String,
                val profilPicturePath: String?) {
    constructor():this("", "", null )
}