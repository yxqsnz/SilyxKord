package dev.yxqsnz.silyx.command.handler
import dev.yxqsnz.silyx.command.vanilla.`fun`.MeowCommand
import dev.yxqsnz.silyx.command.vanilla.info.BotInfoCommand
import dev.yxqsnz.silyx.command.vanilla.misc.PingCommand
import dev.yxqsnz.silyx.command.vanilla.misc.RandomWaifuCommand
import uai.yxqsnz.silyx.command.vanilla.`fun`.*
import uai.yxqsnz.silyx.command.vanilla.misc.*
import uai.yxqsnz.silyx.command.vanilla.info.*
class CommandRegister(val commandManager: CommandManager) {
    fun registerCommands() {
        commandManager.register(PingCommand())
        commandManager.register(BotInfoCommand())
        commandManager.register(MeowCommand())
        commandManager.register(RandomWaifuCommand())

    }
}