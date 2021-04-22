package dev.yxqsnz.services

import dev.yxqsnz.cache.commands
import dev.yxqsnz.classes.command.TextCommand
/**
* essa classe e a classe que mexe com os comandos
* tipo registrar, pegar comandos etc.
*/
object CommandService {
    fun registerCommand(command: TextCommand) =
        commands.add(command)

    /**
     * Uso: `CommandService ["ping"]`
     */
    operator fun get(command: String): TextCommand? {
        var vanillaCommand = commands.firstOrNull { it.options.aliases.contains(command.toLowerCase()) }
        if (vanillaCommand == null) vanillaCommand = commands.firstOrNull { it.options.name.equals(command, ignoreCase = true) }
        return vanillaCommand
    }


}