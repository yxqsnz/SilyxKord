package dev.yxqsnz.silyx.command.handler
import dev.yxqsnz.silyx.command.vanilla.`fun`.*
import dev.yxqsnz.silyx.command.vanilla.misc.*
import dev.yxqsnz.silyx.command.vanilla.info.*
import dev.yxqsnz.silyx.command.vanilla.utils.ReadCsvCommand
import dev.yxqsnz.silyx.command.vanilla.dev.*
class CommandRegister(private val commandManager: CommandManager) {
    fun registerCommands() {
        commandManager.register(PingCommand())
        commandManager.register(BotInfoCommand())
        commandManager.register(MeowCommand())
        commandManager.register(RandomWaifuCommand())
        commandManager.register(ReadCsvCommand())
        commandManager.register(XbanCommand())
    }
}