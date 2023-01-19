package com.example.whatsappui.ui.models

import java.util.*

data class Chat (
    val userAvtar : String,
    val userName:String,
    val lastMessage:String,
    val time: Date,
    val unreadMessagesCount :Int,
)