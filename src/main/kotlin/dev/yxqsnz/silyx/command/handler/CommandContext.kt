package dev.yxqsnz.silyx.command.handler
import Silyx
import dev.kord.core.Kord
import dev.kord.core.entity.Message
import dev.yxqsnz.silyx.Config
import dev.yxqsnz.silyx.command.vanilla.misc.PingCommand

abstract class TextCommand(val options: Options) {
    abstract suspend fun exec(context: CommandContext)

    abstract class Options(val name: String) {
        open var description: String? = "Comando sem descrição"
        open var aliases: List<String> = listOf()
        open var guildOnly: Boolean = false
        open var onlyDev: Boolean = false
    }

}

class CommandContext(
    val client: Kord,
    val message: Message,
    val args: List<String>,
    ) {
    suspend fun messageReply(content: String) {
        this.message.channel.createMessage("\uD83D\uDD39 • ${this.message.author!!.mention} $content")
    }
}
