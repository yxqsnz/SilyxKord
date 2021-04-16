package uai.yxqsnz.silyx.command.handler
import Silyx
import dev.kord.core.Kord
import dev.kord.core.entity.Message
import uai.yxqsnz.silyx.Config
import uai.yxqsnz.silyx.command.vanilla.misc.PingCommand

abstract class TextCommand(val options: Options) {
    abstract suspend fun exec(context: CommandContext)

    abstract class Options(val name: String) {
        abstract val description: String?
        abstract val aliases: List<String>?
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
