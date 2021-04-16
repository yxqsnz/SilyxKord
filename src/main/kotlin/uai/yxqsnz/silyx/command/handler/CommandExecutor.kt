package uai.yxqsnz.silyx.command.handler
import Silyx
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on

class CommandExecutor(private val silyx: Silyx) {
    fun startExecutor() = silyx.kord.on<MessageCreateEvent> {
        val prefix = silyx.config.prefix
        if (this.message.author?.isBot == true ||
            !this.message.content.startsWith(prefix)
            || this.message.getGuildOrNull() == null) return@on
        val args = this.message.content.substring(prefix.length, this.message.content.length).trim().split(" ")
        val context = CommandContext(
            silyx.kord,
            this.message,
            args.drop(1)
        )
        val command = silyx.commandManager[args[0]] ?: return@on

        command.exec(context)

    }

}