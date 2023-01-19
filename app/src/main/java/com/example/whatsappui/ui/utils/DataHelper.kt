package com.example.whatsappui.ui.utils

import com.example.whatsappui.ui.models.Chat
import io.bloco.faker.Faker

object DataHelper {

    private val faker = Faker()

    fun provideChats() : List<Chat> {
        val list = ArrayList<Chat>()
        repeat(20){
            list.add(
                Chat(
                    userAvtar = faker.avatar.image(),
                    userName = faker.name.name(),
                    lastMessage = faker.lorem.sentence(),
                    time = faker.time.backward(10),
                    unreadMessagesCount = faker.number.between(0,100)
                )
            )
        }
        return list
    }
}