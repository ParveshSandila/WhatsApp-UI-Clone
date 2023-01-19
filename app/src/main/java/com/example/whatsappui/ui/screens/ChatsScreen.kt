package com.example.whatsappui.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.whatsappui.ui.comosables.ChatListItem
import com.example.whatsappui.ui.utils.DataHelper

@Composable
fun ChatsScreen(
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ){
        items(DataHelper.provideChats()){ chat ->
            ChatListItem(chat = chat){

            }
        }
    }
}