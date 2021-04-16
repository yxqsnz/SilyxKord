package uai.yxqsnz.silyx.command.vanilla.misc
import dev.kord.core.behavior.edit
import dev.kord.core.behavior.reply
import uai.yxqsnz.silyx.command.handler.CommandContext
import uai.yxqsnz.silyx.command.handler.TextCommand
import javax.
class PingCommand: TextCommand(Options){
    companion object Options:TextCommand.Options("ping") {
        override val description: String = "Pong"
        override val aliases: List<String> = listOf("p","pingCommand")
    }

    override suspend fun exec(context: CommandContext) {
        val now = System.currentTimeMillis()
        val gatewayPing = context.client.gateway.averagePing

        val msg = context.message.reply {

            content = ":ping_pong: **•** Pong!\n:small_orange_diamond: • **DISCORD API**: `...`\n:small_blue_diamond: • **Gateway**: `$gatewayPing` ms"
        }

        msg.edit {
            val diff = System.currentTimeMillis() - now
            content = ":ping_pong: **•** Pong!\n:small_blue_diamond: • **DISCORD API**: `${diff}ms`\n:small_blue_diamond: • **Gateway**: `$gatewayPing`"
        }

    }
}