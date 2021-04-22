package dev.yxqsnz.classes.command

import dev.kord.core.Kord
import dev.kord.core.behavior.reply

import dev.kord.core.entity.Message
import dev.kord.rest.builder.message.EmbedBuilder

class CommandContext (
    val client: Kord,
    val message: Message,
    val args: List<String>
    ) {
    suspend fun inlineReply(messageContent: String) {
        this.message.reply {
            content = "\uD83D\uDD39 • $messageContent"
        }
    }
    suspend fun inlineReply(rEmbed: EmbedBuilder) {
        this.message.reply {
            embed = rEmbed
        }
    }

    suspend fun reply(content: String) {
        this.message.channel.createMessage("\uD83D\uDD39 • ${this.message.author!!.mention} $content")
    }
}
