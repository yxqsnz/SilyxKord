package dev.yxqsnz.silyx.command.vanilla.misc

import dev.kord.core.behavior.edit
import dev.kord.core.behavior.reply
import dev.yxqsnz.classes.command.*

class PingCommand: TextCommand(Options){
    companion object Options: TextCommand.Options("ping") {
        override var description: String? = "Pong"
        override var aliases: List<String> = listOf("p", "latencia", "latency")
    }

    override suspend fun exec(context: CommandContext) {
        val now = System.currentTimeMillis()
        val gatewayPing = context.client.gateway.averagePing

        val msg = context.message.reply {
            content = ":ping_pong: **•** Pong!\n:small_orange_diamond: • **DISCORD API**: `...`\n:small_blue_diamond: • **Gateway**: `$gatewayPing`"
        }

        msg.edit {
            val diff = System.currentTimeMillis() - now
            content = ":ping_pong: **•** Pong!\n:small_blue_diamond: • **DISCORD API**: `${diff}ms`\n:small_blue_diamond: • **Gateway**: `$gatewayPing`"
        }

    }
}