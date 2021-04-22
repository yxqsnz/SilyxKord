package dev.yxqsnz.silyx.command.handler
// comandos...
import dev.yxqsnz.silyx.command.vanilla.`fun`.*
import dev.yxqsnz.silyx.command.vanilla.misc.*
import dev.yxqsnz.silyx.command.vanilla.info.*
import dev.yxqsnz.silyx.command.vanilla.utils.*
import dev.yxqsnz.silyx.command.vanilla.dev.*

// fim.

import dev.yxqsnz.services.CommandService

/**
 * Essa função vai registrar todos os comandos
 */
fun registerCommands() {
        CommandService.registerCommand(PingCommand())
        CommandService.registerCommand(BotInfoCommand())
        CommandService.registerCommand(MeowCommand())
        CommandService.registerCommand(RandomWaifuCommand())
        CommandService.registerCommand(ReadCsvCommand())
        CommandService.registerCommand(XbanCommand())
        CommandService.registerCommand(RamInfoCommand())
}
