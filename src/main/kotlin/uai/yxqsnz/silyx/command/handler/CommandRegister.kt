package uai.yxqsnz.silyx.command.handler
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